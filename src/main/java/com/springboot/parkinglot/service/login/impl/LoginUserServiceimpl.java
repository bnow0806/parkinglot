package com.springboot.parkinglot.service.login.impl;

import com.springboot.parkinglot.controller.login.LoginUser;
import com.springboot.parkinglot.controller.login.LoginUserDto;
import com.springboot.parkinglot.repository.login.IUserDao;
import com.springboot.parkinglot.service.login.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginUserServiceimpl implements LoginUserService {

    private final IUserDao iUserDao;

    @Autowired
    public LoginUserServiceimpl(IUserDao iUserDao){
        this.iUserDao = iUserDao;
    }

    @Override
    public LoginUserDto getLoginUser(String username) {
        LoginUser loginUser = iUserDao.findByUsername(username).orElseThrow(
                ()->new RuntimeException("findByUsername not found"));

        //type casting : LoginUser -> LoginUserDto
        return LoginUserDto.builder()
                        .username(loginUser.getUsername())
                        .email(loginUser.getEmail())
                        .password(loginUser.getPassword())
                        .role(loginUser.getRole())
                        .active(loginUser.getActive())
                        .build();
    }

    @Override
    public List<LoginUserDto> getAllLoginUser() {
        List<LoginUser> listLoginUser = iUserDao.findAll();

        //type casting : LoginUser -> LoginUserDto
        //String username, String password, String email, String active, String role
        return listLoginUser.stream()
                .map(user -> new LoginUserDto(user.getUsername(), user.getPassword(),
                user.getEmail(), user.getActive(), user.getRole()))
                .collect(Collectors.toList());
    }
}
