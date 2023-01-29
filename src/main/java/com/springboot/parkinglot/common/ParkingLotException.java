package com.springboot.parkinglot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParkingLotException extends RuntimeException{

    private int httpStatusCode;
    private int detailedCode;
    private String message;

    public ParkingLotException(int httpStatusCode, int detailedCode, String message){
        this.httpStatusCode =httpStatusCode;
        this.detailedCode =detailedCode;
        this.message = message;
    }
}