package com.springboot.parkinglot.controller.user;
import com.springboot.parkinglot.common.ParkingLotException;
import com.springboot.parkinglot.controller.common.CheckValidity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor
@Data
public class UserRequest implements CheckValidity {

    private String id;

    private String password;

    private String name;

    public UserRequest(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    @Override
    public void check(){
        if(id.length() <6){
            throw new ParkingLotException(404, 1, "id is too short");
        }
        if(password.length() <6){
            throw new ParkingLotException(404, 2, "password is too short");
        }
        if(name.length() <6){
            throw new ParkingLotException(404, 3, "name is too short");
        }
    }
}
