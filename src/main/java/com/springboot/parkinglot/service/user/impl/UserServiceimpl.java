package com.springboot.parkinglot.service.user.impl;

import com.springboot.parkinglot.controller.team.Team;
import com.springboot.parkinglot.controller.user.User;
import com.springboot.parkinglot.controller.user.UserRequest;
import com.springboot.parkinglot.controller.user.UserDto;
import com.springboot.parkinglot.repository.team.TeamRepository;
import com.springboot.parkinglot.repository.user.*;
import com.springboot.parkinglot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {


    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public UserServiceimpl(UserRepository userRepository, TeamRepository teamRepository)
    {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public UserDto saveUser(UserRequest userRequest){
        User user = new User();
        user.setId(userRequest.getId());
        user.setPassword(userRequest.getPassword());
        user.setName(userRequest.getName());

        //matching Team
//        Team team = new Team();
//        team.setName("3팀");
//        teamRepository.save(team);
//
//        user.setTeam(team);

        User savedUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setNumber(savedUser.getNumber());
        userDto.setId(savedUser.getId());
        userDto.setPassword(savedUser.getPassword());
        userDto.setName(savedUser.getName());

        return userDto;
    }

    @Override
    public UserDto getUser(Long number) {
        User user = userRepository.getById(number);

        UserDto userDto = new UserDto();
        userDto.setNumber(user.getNumber());
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());

        return userDto;
    }

    @Override
    public UserDto chageUserName(Long number, String id, String password) throws Exception {

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
        UserDto userDto = new UserDto();
        userDto.setNumber(updatedUser.getNumber());
        userDto.setId(updatedUser.getId());
        userDto.setPassword(updatedUser.getPassword());
        userDto.setName(updatedUser.getName());

        return userDto;
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
