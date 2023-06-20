package com.springboot.parkinglot.controller.login;

import lombok.Data;

@Data
public class LoginViewModel {

    private String userEmail; // 본실습은 username -> email 변경하여 진행
    private String password;
}
