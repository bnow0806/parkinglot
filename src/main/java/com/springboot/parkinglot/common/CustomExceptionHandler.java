package com.springboot.parkinglot.common;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

        @ExceptionHandler(CustomException.class)
        public ErrorMessage exception(CustomException e){
            e.printStackTrace();

            ErrorMessage message = new ErrorMessage();
            message.setMessage(e.getMessage()); //how to make diverse error message in CustomException?
            message.setCode(e.getCode());
            return message;
    }
}
@Data
class ErrorMessage{
    private String message;
    private int code;
}