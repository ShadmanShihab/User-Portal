package com.registration.userportal.controller;


import com.registration.userportal.domain.ResetPasswordDto;
import com.registration.userportal.domain.UserDto;
import com.registration.userportal.model.User;
import com.registration.userportal.repository.RoleRepository;
import com.registration.userportal.repository.UserRepository;
import com.registration.userportal.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, UserService userService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping("/registration")
    public String home(Model m) {
        m.addAttribute("user", new UserDto());
        m.addAttribute("roles", roleRepository.findAllByEnabledRole());
        m.addAttribute("message", "");
        return "registration";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute() UserDto userDto, Model m) throws ParseException {
        String isExist = userService.saveUser(userDto);
        if (isExist.equals("exist")) {
            m.addAttribute("message", "User with this email already exist. Enter a new email to complete registration");
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

    @GetMapping("/test")
    public String showTest(){
        return "home";
    }

    @GetMapping("/reset-pass")
    public String showResetPasswordPage(Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        m.addAttribute("message", " ");
        m.addAttribute("reset", new ResetPasswordDto(user.getUserId()));
        return "reset-password";
    }

    @PostMapping("/reset-pass/save")
    public String savePassword(@ModelAttribute("reset") ResetPasswordDto resetPasswordDto, Model m) {
        String resetPasswordValidation = userService.savePassword(resetPasswordDto);
        return resetPasswordValidation;
    }
}
