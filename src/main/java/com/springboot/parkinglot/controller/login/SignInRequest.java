package com.springboot.parkinglot.controller.login;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String userEmail;
    private String password;
    private String ROLE;    //ROLE_ADMIN or ROLE_MANAGER
}
