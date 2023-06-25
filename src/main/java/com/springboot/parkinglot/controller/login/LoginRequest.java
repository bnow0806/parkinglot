package com.springboot.parkinglot.controller.login;

import lombok.Data;

@Data
public class LoginRequest {

    private String userEmail;
    private String password;

}
