package com.registration.userportal.controller;

import com.registration.userportal.domain.UserDto;
import com.registration.userportal.service.AdminService;
import com.registration.userportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/get-all")
    public List<UserDto> getAllUserOnLoad(){
        return adminService.getAllUserDtos();
    }

    @GetMapping("/get-users/{name}")
    public List<UserDto> getUserByName(@PathVariable("name") String name){
        return adminService.getUserByName( name );
    }


}
