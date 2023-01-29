package com.springboot.parkinglot.controller.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor
@Data
public class UserDto {

    private Long number;

    private String id;

    private String password;

    private String name;

    public UserDto(Long number, String id, String password, String name) {
        this.number = number;
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
