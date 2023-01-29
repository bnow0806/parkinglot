package com.springboot.parkinglot.controller.user;

import com.springboot.parkinglot.common.ParkingLotException;
import com.springboot.parkinglot.controller.common.CheckValidity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangeUserNameRequest implements CheckValidity {

    private Long number;    //added to
    private String id;
    private String password;

    @Override
    public void check() {
        if(id.length() <6){
            throw new ParkingLotException(404, 4, "id is too short");
        }
        if(password.length() <6){
            throw new ParkingLotException(404, 5, "password is too short");
        }
    }
}

