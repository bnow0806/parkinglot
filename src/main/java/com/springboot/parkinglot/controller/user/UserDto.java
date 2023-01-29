package com.springboot.parkinglot.controller.user;
import com.springboot.parkinglot.controller.CheckValidity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
//@AllArgsConstructor
@Data
public class UserDto implements CheckValidity {     //UserRequest

    private String id;

    private String password;

    private String name;

    public UserDto(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }



}
