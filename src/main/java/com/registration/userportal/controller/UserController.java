package com.registration.userportal.controller;


import com.registration.userportal.domain.UserDto;
import com.registration.userportal.repository.RoleRepository;
import com.registration.userportal.repository.UserRepository;
import com.registration.userportal.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String home(Model m){
        m.addAttribute("user", new UserDto());
        m.addAttribute("roles", roleRepository.findAllByEnabledRole());
        m.addAttribute("message", "");
        return "registration";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") UserDto userDto, Model m)  {
        String isExist = userService.saveUser(userDto);

        if (isExist.equals("exist")) {
            m.addAttribute("message", "User with this email already exist.Use a new email.");
            m.addAttribute("user", new UserDto());
            m.addAttribute("roles", roleRepository.findAllByEnabledRole());
            return "registration";
        } else {
            m.addAttribute("login_message", "Registration sucessfull. Please login to continue.");
            return "login";
        }
    }


    @GetMapping("/home")
    public String userHomePage(Model m) {
        m.addAttribute("user", userService.getUser());
        return "user";
    }
}
