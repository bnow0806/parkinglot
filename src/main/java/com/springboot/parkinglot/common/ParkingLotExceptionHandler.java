package com.springboot.parkinglot.common;

import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ParkingLotExceptionHandler {

        @ExceptionHandler(ParkingLotException.class)
        public ErrorMessage exception(ParkingLotException e){   //exception comes in here
            e.printStackTrace();

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrMessage(e.getMessage());
            errorMessage.setErrCode(e.getDetailedCode());
            return errorMessage;
    }
}

@Data
class ErrorMessage{
    private String errMessage;
    private int errCode;
}