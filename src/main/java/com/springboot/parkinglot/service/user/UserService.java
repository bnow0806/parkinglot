package com.springboot.parkinglot.service.user;

import com.springboot.parkinglot.controller.user.UserRequest;
import com.springboot.parkinglot.controller.user.UserDto;

public interface UserService {

    UserDto saveUser(UserRequest userRequest);

    UserDto getUser(Long number);

    UserDto chageUserName(Long number, String id, String password) throws Exception;

    void deleteUser(Long number) throws Exception;
}
