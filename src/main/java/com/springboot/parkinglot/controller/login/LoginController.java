package com.springboot.parkinglot.controller.login;

import com.springboot.parkinglot.service.login.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class LoginController {

    private  final LoginUserService loginUserService;

    @Autowired
    public LoginController(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @GetMapping("/api/v1/login/admin") // admin 권한에게만
    public String loginAdmin() {

        return "Success ADMIN";
    }

    @GetMapping("/api/v1/login/manager") // manager 권한에게만
    public String loginManager() {

        return "Success MANAGER";
    }

    @GetMapping("/api/v1/alluserdata") //get all user data
    public ResponseEntity< List<LoginUserDto> > allUserData(){
        List<LoginUserDto> listLoginUserDto = loginUserService.getAllLoginUser();

        return ResponseEntity.status(HttpStatus.OK).body(listLoginUserDto);
    }

    @GetMapping("/api/v1/myuserdata")   //get my user data
    public ResponseEntity<LoginUserDto> myUserData(String username){
        LoginUserDto loginUserDto = loginUserService.getLoginUser(username);

        return ResponseEntity.status(HttpStatus.OK).body(loginUserDto);
    }

    @PostMapping("/signin")
    public  ResponseEntity<LoginUserDto> signIn
            (SignInRequest signInRequest){

        LoginUserDto loginUserDto = loginUserService.signInLoginUser(signInRequest);

        return ResponseEntity.status(HttpStatus.OK).body(loginUserDto);
    }

    @GetMapping("/api/v1/login/common")
    public ResponseEntity<LoginResultDto> loginCommon
            (LoginRequest loginRequest){

        LoginResultDto loginResultDto = loginUserService.loginLoginUser(loginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(loginResultDto);
    }
}
