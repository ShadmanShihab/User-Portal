package com.registration.userportal.domain;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private long userId;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public ResetPasswordDto(long userId){
        this.userId = userId;
    }
}
