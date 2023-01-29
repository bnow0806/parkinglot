package com.springboot.parkinglot.controller.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangeUserNameDto {

    private Long number;    //added to
    private String id;
    private String password;

}

