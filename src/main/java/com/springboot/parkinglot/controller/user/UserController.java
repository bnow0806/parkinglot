package com.springboot.parkinglot.controller.user;

import com.springboot.parkinglot.common.ParkingLotException;
import com.springboot.parkinglot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/user")
public class UserController{

    private  final UserService userService;

    private final static Logger logger= Logger.getGlobal();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUserRequest(@RequestBody UserRequest userRequest)
    throws ParkingLotException{

        //checkValidity
        //System.out.println("length : "+ userDto.getId().length());
        userRequest.check();

        UserDto userDto = userService.saveUser(userRequest);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping()
    public  ResponseEntity<UserDto> getUserRequest(Long number){
        UserDto userDto = userService.getUser(number);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PutMapping()
    public ResponseEntity<UserDto> changeUser
            (@RequestBody ChangeUserNameRequest changeUserNameRequest) throws Exception {

        //checkValidity
        
        UserDto userDto = userService.chageUserName(
                changeUserNameRequest.getNumber(),
                changeUserNameRequest.getId(),
                changeUserNameRequest.getPassword()
        );
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @DeleteMapping()
    public  ResponseEntity<String> deleteUser(Long number) throws Exception{
        userService.deleteUser(number);

        return  ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }
    
}
