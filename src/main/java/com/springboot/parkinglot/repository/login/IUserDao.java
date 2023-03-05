package com.springboot.parkinglot.repository.login;

import com.springboot.parkinglot.controller.login.LoginUser;
import com.springboot.parkinglot.controller.login.LoginUserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserDao extends JpaRepository<LoginUser, Long> {
    Optional<LoginUser> findByEmail(String email);

    Optional<LoginUser> findByUsername(String username);

    List<LoginUser> findAll();
}
