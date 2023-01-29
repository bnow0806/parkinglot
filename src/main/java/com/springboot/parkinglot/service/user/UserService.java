package com.springboot.parkinglot.service.user;

import com.springboot.parkinglot.controller.user.User;
import com.springboot.parkinglot.controller.user.UserDto;
import com.springboot.parkinglot.controller.user.UserResponseDto;
import org.springframework.data.jpa.repository.Query;

public interface UserService {

    UserResponseDto saveUser(UserDto userDto);

    UserResponseDto getUser(Long number);

    UserResponseDto chageUserName(Long number, String id, String password) throws Exception;

    void deleteUser(Long number) throws Exception;
}
