package com.springboot.parkinglot.service.login;

import com.springboot.parkinglot.controller.login.LoginUserDto;
import com.springboot.parkinglot.controller.user.UserDto;

import java.util.List;

public interface LoginUserService {

    LoginUserDto getLoginUser(String username);

    List<LoginUserDto> getAllLoginUser();
}
