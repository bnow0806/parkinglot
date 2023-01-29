package com.springboot.parkinglot.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.parkinglot.controller.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where id = :id and password = :password")
    User findUser(@Param("id") String id, @Param("password")String password);   //@Param added
}
