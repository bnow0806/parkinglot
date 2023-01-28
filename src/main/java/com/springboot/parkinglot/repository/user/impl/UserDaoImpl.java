package com.springboot.parkinglot.repository.user.impl;

import com.springboot.parkinglot.controller.user.User;
import com.springboot.parkinglot.repository.user.UserDao;
import com.springboot.parkinglot.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {   //delete

    private final UserRepository userRepository;

    @Autowired
    public UserDaoImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User insertUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User selectUser(Long number) {
        User selectedUser = userRepository.getById(number);
        return selectedUser;
    }
}
