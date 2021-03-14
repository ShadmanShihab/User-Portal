package com.registration.userportal.domain;

import lombok.Data;

@Data
public class UserDto {
    private long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String birthdate;
    private int age;
    private String email;
    private String password;
    private long roleId;
    private String roleName;
}
