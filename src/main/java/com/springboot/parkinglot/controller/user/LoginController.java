package com.springboot.parkinglot.controller.user;

import com.springboot.parkinglot.repository.user.UserRepository;
import com.springboot.parkinglot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private  final UserRepository userRepository;   //directly use repository?

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/signin")
    public String signin(String inputId, String inputPassword){

        User user = userRepository.findUser(inputId, inputPassword);

        if(user != null){
            return "loginOK";
        }
        return "loginFail";
    }
}
