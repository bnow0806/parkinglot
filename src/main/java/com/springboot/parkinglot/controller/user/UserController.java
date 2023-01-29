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

<<<<<<< HEAD
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
=======
        userResponseDto = userDto
        userService.saveUser(userResponseDto);  //똑같은 값이면 dto 아니여도 됨. service->controller : entity xxx / dto

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
>>>>>>> develop
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
<<<<<<< HEAD
    
=======


    @Override
    public void checkString(String value) {

        if(value==null){
            logger.info("null");
        }
        if(value!=null && value.length()>20){
            logger.info("size is more than 20");
        }
        if(value.isBlank()){
            logger.info("isBlank");
        }

    }

    @Override
    public void checkLong(Long value) {
        if(value==null){
            logger.info("null");
        }
        if(value!=null && Long.toString(value).length()>20){
            logger.info("size is more than 20");
        }
        if(Long.toString(value).isBlank()){
            logger.info("isBlank");
        }
    }

    @Override   //how to make diverse input and same name?
    public void check(String id, String password, String name) throws CustomException{
        if(id.length() <6){
            throw new CustomException(404, -1, "sdssd");
        }

        if(password.length() <6){
            throw new CustomException();
        }

        if(name.length() <6){
            throw new CustomException();
        }
    }
>>>>>>> develop
}
