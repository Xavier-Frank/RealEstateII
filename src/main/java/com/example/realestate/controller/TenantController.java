package com.example.realestate.controller;

import com.example.realestate.dao.TenantsRepository;
import com.example.realestate.model.Tenants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TenantController {
    @Autowired
    TenantsRepository tenantsRepository;

    @GetMapping("/index")
    public String viewHomepage(){
        return "index";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new Tenants());
        return "registration";
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return "loginform";
    }
    @PostMapping("/registration_process")
    public String registerUser(Tenants tenants){


        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(tenants.getPassword());
        tenants.setPassword(encodedPassword);


        tenantsRepository.save(tenants);
        return "redirect:/login?success";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/index?logout";
    }


    //other pages from frontend
//    @GetMapping("/about")
//    public String displayAboutPage(){
//        return "about";
//    }
//    @GetMapping("/properties")
//    public String displayPropetiesPage(){
//        return "properties";
//    }
//    @GetMapping("/contact")
//    public String displayContactPage(){
//        return "contact";
//    }
    @GetMapping("/tenants")
    public String viewHomePage(Model model){
        model.addAttribute("listTenants", tenantServiceImplementation.getAllTenants());
        return "tenant_index";
    }
    //Method Handler for addNewTenant request
    @GetMapping("/addNewTenant")
    public String addNewTenant(Model model) {
        //Create model attribute to bind data accessed from thymeleaf template(this empty tenant object)
        Tenant tenant = new Tenant();
        model.addAttribute("tenant", tenant);
        return "new_tenant";
    }

    //Updating method
    //@PostMapping("/saveNewTenant")
    @RequestMapping("/saveNewTenant")
    //Bind the model attributes data to the tenant
    public String saveNewTenant(@ModelAttribute("tenant") Tenant tenant) {
        // save tenant to database
        tenantServiceImplementation.saveTenant(tenant);
        //redirect to home page
        return "redirect:/tenants";

    }

    //Method Handler for newTenant request
    //@PutMapping("/edit/{phoneNumber}")
    @RequestMapping("/editTenant/{phoneNumber}")
    public ModelAndView updateTenant(@PathVariable(name = "phoneNumber") Long phoneNumber){

        ModelAndView modelAndView = new ModelAndView("edit_tenant");

        Optional<Tenant> tenant = tenantServiceImplementation.getTenantByPhoneNumber(phoneNumber);
        modelAndView.addObject("tenant",tenant);

        return modelAndView;
    }

    //@DeleteMapping("/delete/{phoneNumber}")
    @RequestMapping("/deleteTenant/{phoneNumber}")
    public String deleteTenant(@PathVariable(name = "phoneNumber") Long phoneNumber){
        tenantServiceImplementation.deleteTenant(phoneNumber);
        return "redirect:/tenants";
    }


}
