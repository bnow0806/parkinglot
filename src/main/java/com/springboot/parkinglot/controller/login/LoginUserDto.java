package com.springboot.parkinglot.controller.login;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@Data
public class LoginUserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String active;
    private String role;

    @Builder
    public LoginUserDto(String username, String password, String email, String active, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.role = role;
    }
}
