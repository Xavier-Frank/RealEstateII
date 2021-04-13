package com.example.realestate.controller;

import com.example.realestate.dao.UserRepository1;
import com.example.realestate.model.User1;
import com.example.realestate.service.EmailServiceImpl;
import com.example.realestate.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository1 userRepository1;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //display forgot password page
    @GetMapping("/forgotPassword")
    public ModelAndView forgotPasswordPage(){
        return new ModelAndView("/forgotPassword");

    }

    //process form submission from forgot password page
    @PostMapping("/forgotPassword")
    public ModelAndView processForgotPasswordPage(ModelAndView modelAndView, @RequestParam("email") String userEmail,
                                                  HttpServletRequest request){
        //fetch user in db by email
        Optional<User1> optional = Optional.ofNullable(userRepository1.findByEmail(userEmail));

        if (!optional.isPresent()){
//            modelAndView.addObject("errorMessage", "Email accout")
            return new ModelAndView("forgotPassword?error");
        }else {
            //generate token for reset password
            User1 user1 = optional.get();
            user1.setResetToken(UUID.randomUUID().toString());

            //save the token in db
            userService.saveUser(user1);

            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            //Create the message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("supportcenter@gmail.com");
            passwordResetEmail.setTo(user1.getEmail());
            passwordResetEmail.setSubject("Complete your Real Estate website request");
            passwordResetEmail.setText("Click the link to complete your password reset" + url + "/resetPasswordPage?token=" + user1.getResetToken());

            //send the email
            emailService.sendEmail(passwordResetEmail);

            //add a sucsess message
            modelAndView.setViewName("redirect:/forgotPassword?success");

        }
        return modelAndView;
    }

    //display reset password page
    @GetMapping("/resetPasswordPage")
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token){
        Optional<User1> user1 = userService.findUserByResetToken(token);

        if (user1.isPresent()){
            modelAndView.addObject("resetToken", token);
        }else {
            modelAndView.addObject("errorMessage", "Invalid link");
        }
        modelAndView.setViewName("resetPassword");

        return modelAndView;
    }

    //process the reset form
    @PostMapping("/resetPassword")
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams,
                                       RedirectAttributes attributes){

        //find user linked to the token
        Optional<User1> user1 = userService.findUserByResetToken(requestParams.get("token"));

        if (user1.isPresent()){
            User1 resetUser = user1.get();
            //set new password
            resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

            //set the token to null so that it cannot be used again
            resetUser.setResetToken(null);

            //saving the user
            userService.saveUser(resetUser);

            //redireccting attributes
            attributes.addFlashAttribute("successMessage", "Successfully reset your password");

            modelAndView.setViewName("redirect:/login");
            return modelAndView;

        }else {
            modelAndView.addObject("errorMessage", "Invalid password link");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }

    //handling the exception of accessing the resetPassword page without token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingTokens(MissingServletRequestParameterException ex){
        return new ModelAndView("redirect:/login");
    }

}