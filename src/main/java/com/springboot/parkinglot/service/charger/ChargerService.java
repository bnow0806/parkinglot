package com.springboot.parkinglot.service.charger;

import com.springboot.parkinglot.controller.charger.ChargerCreateRequest;
import com.springboot.parkinglot.controller.charger.ChargerDto;
import com.springboot.parkinglot.controller.charger.ChargerUpdateRequest;
import com.springboot.parkinglot.controller.login.LoginUserDto;
import com.springboot.parkinglot.controller.user.UserDto;
import com.springboot.parkinglot.controller.user.UserRequest;

import java.util.List;

public interface ChargerService {

    ChargerDto saveCharger(ChargerCreateRequest chargerCreateRequest);

    List<ChargerDto> getCharger();

    ChargerDto chageCharger(ChargerUpdateRequest chargerUpdateRequest);

    void deleteCharger(Long number);
}
