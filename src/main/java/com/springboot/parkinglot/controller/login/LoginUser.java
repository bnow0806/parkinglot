package com.springboot.parkinglot.controller.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //added because of "Not a managed type:"
@Getter
@NoArgsConstructor
public class LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String username;
    private String password;
    private String email;  // user를 찾을 때 email을 이용하여 찾는다.
    private String active; // 1: active, 0: inactive
    private String role;

    @Builder
    public LoginUser(String username, String password, String email, String active, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.role = role;
    }
}
