package com.springboot.parkinglot.controller.login;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class LoginResultDto {

    private String token;

    private boolean success;

    private int code;

    private String msg;

    @Builder
    public LoginResultDto(String token, boolean success, int code, String msg) {
        this.token = token;
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
}
