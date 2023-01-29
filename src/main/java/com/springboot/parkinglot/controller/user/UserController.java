package com.springboot.parkinglot.controller.user;

import com.springboot.parkinglot.common.CustomException;
import com.springboot.parkinglot.controller.CheckValidity;
import com.springboot.parkinglot.service.user.UserService;
import lombok.Data;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/user")
public class UserController implements CheckValidity {

    private  final UserService userService;

    private final static Logger logger= Logger.getGlobal();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> createUserRequest(@RequestBody UserDto userDto)
    throws CustomException{

        //checkValidity
//        checkString(userDto.getId());
//        checkString(userDto.getPassword());
//        checkString(userDto.getName());

        //new checkValidity methode
        check(userDto.getId(),userDto.getPassword(),userDto.getName());   //if name is shorter than 6, CustomException arises

        userResponseDto = userDto
        userService.saveUser(userResponseDto);  //똑같은 값이면 dto 아니여도 됨. service->controller : entity xxx / dto

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @GetMapping()
    public  ResponseEntity<UserResponseDto> getUserRequest(Long number){
        UserResponseDto userResponseDto = userService.getUser(number);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @PutMapping()
    public ResponseEntity<UserResponseDto> changeUser
            (@RequestBody ChangeUserNameDto changeUserNameDto) throws Exception {

        //checkValidity
        changeUserNameRequest.check();

        UserDto userDto = userService.chageUserName(
                changeUserNameRequest.getNumber(),
                changeUserNameRequest.getId(),
                changeUserNameRequest.getPassword()

        );
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @DeleteMapping()
    public  ResponseEntity<String> deleteUser(Long number) throws Exception{
        userService.deleteUser(number);

        return  ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }


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
}
