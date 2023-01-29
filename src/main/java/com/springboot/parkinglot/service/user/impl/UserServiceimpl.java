package com.springboot.parkinglot.service.user.impl;

import com.springboot.parkinglot.controller.user.User;
import com.springboot.parkinglot.controller.user.UserDto;
import com.springboot.parkinglot.controller.user.UserResponseDto;
import com.springboot.parkinglot.repository.user.*;
import com.springboot.parkinglot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

    private  final UserDao userDao;
    private final UserRepository userRepository;    //made for deleting dao file

    @Autowired
    public UserServiceimpl(UserDao userDao, UserRepository userRepository)
    {   this.userDao = userDao;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());

        User savedUser = userDao.insertUser(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setNumber(savedUser.getNumber());
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setName(savedUser.getName());

        return userResponseDto;
    }

    @Override
    public UserResponseDto getUser(Long number) {
        User user = userDao.selectUser(number);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setNumber(user.getNumber());
        userResponseDto.setId(user.getId());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setName(user.getName());

        return userResponseDto;
    }

    @Override
    public UserResponseDto chageUserName(Long number, String id, String password) throws Exception {

        Optional<User> selectedUser = userRepository.findById(number);
        User updatedUser;

        if(selectedUser.isPresent()){
            User user = selectedUser.get();

            user.setId(id);
            user.setPassword(password);

            updatedUser = userRepository.save(user);
        }
        else{
            throw new Exception();
        }

        //Dao - updatedUser //service - UserResponseDto
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setNumber(updatedUser.getNumber());
        userResponseDto.setId(updatedUser.getId());
        userResponseDto.setPassword(updatedUser.getPassword());
        userResponseDto.setName(updatedUser.getName());

        return userResponseDto;
    }

    @Override
    public void deleteUser(Long number) throws Exception {
        Optional<User> selectedUser = userRepository.findById(number);

        if(selectedUser.isPresent()){
            User user = selectedUser.get();

            userRepository.delete(user);
        }else{
            throw new Exception();
        }
    }


}
