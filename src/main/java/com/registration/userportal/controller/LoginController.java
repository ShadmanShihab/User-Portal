package com.registration.userportal.controller;

import com.registration.userportal.model.User;
import com.registration.userportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(Model m){
        //m.addAttribute("login_message", "");
        return "login";
    }

    @RequestMapping("/login-success")
    public String loginSuccess(Model m){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = user.getRole().getAuthority();

        if(role.equals("ADMIN")){
            return "redirect:/admin";
        }
        else{
            return "redirect:/user/home";
        }
    }

    @GetMapping("/admin")
    public String adminPage(Model m){
        return "admin";
    }
}
