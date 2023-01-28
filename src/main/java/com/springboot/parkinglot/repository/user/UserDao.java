package com.springboot.parkinglot.repository.user;

import com.springboot.parkinglot.controller.user.User;

public interface UserDao {  //delete

    User insertUser(User user);

    User selectUser(Long number);
}
