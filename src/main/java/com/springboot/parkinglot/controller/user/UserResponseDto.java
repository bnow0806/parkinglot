package com.springboot.parkinglot.controller.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor
@Data
public class UserResponseDto {      //UserDto

    private Long number;

    private String id;

    private String password;

    private String name;

    public UserResponseDto(Long number, String id, String password, String name) {
        this.number = number;
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
