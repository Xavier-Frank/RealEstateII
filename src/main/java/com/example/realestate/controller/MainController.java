package com.example.realestate.controller;

import com.example.realestate.dao.UserRepository1;
import com.example.realestate.model.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    UserRepository1 userRepository1;

    @GetMapping("/index")
    public String viewHomepage(){
        return "index";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User1());
        return "registration";
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return "loginform";
    }
    @PostMapping("/registration_process")
    public String registerUser(User1 user1){


        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user1.getPassword());
        user1.setPassword(encodedPassword);


        userRepository1.save(user1);
        return "redirect:/login?success";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/index?logout";
    }
}
