package com.springboot.parkinglot.controller;

import com.springboot.parkinglot.common.CustomException;
import com.springboot.parkinglot.controller.user.UserDto;

public interface CheckValidity {

    void checkString(String value);

    void checkLong(Long value);

    void check(String id, String password, String name) throws CustomException;
}
