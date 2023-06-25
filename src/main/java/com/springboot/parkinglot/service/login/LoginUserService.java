package com.springboot.parkinglot.service.login;

import com.springboot.parkinglot.controller.login.LoginRequest;
import com.springboot.parkinglot.controller.login.LoginResultDto;
import com.springboot.parkinglot.controller.login.LoginUserDto;
import com.springboot.parkinglot.controller.login.SignInRequest;
//import com.springboot.parkinglot.controller.login.TokenInformation;
import com.springboot.parkinglot.controller.user.UserDto;

import java.util.List;

public interface LoginUserService {

    LoginUserDto getLoginUser(String username);

    List<LoginUserDto> getAllLoginUser();

    LoginUserDto signInLoginUser(SignInRequest signInRequest);

    LoginResultDto loginLoginUser(LoginRequest loginRequest);
}
