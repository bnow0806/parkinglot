package com.springboot.parkinglot.controller.charger;

import com.springboot.parkinglot.common.ParkingLotException;
import com.springboot.parkinglot.controller.user.ChangeUserNameRequest;
import com.springboot.parkinglot.controller.user.UserDto;
import com.springboot.parkinglot.service.charger.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/charger")
public class ChargerController {

    private final ChargerService chargerService;

    @Autowired
    public ChargerController(ChargerService chargerService) {
        this.chargerService = chargerService;
    }

    //Charger 생성
    @PostMapping()
    public ResponseEntity<ChargerDto> createCharger(ChargerCreateRequest chargerCreateRequest){

        ChargerDto chargerDto = chargerService.saveCharger(chargerCreateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(chargerDto);
    }

    //Charger 조회
    @GetMapping()
    public  ResponseEntity<List<ChargerDto>> getCharger(){
        List<ChargerDto> listChargerDto = chargerService.getCharger();  //get all

        return ResponseEntity.status(HttpStatus.OK).body(listChargerDto);
    }

    //Charger 수정
    @PutMapping()
    public ResponseEntity<ChargerDto> changeCharger
            (@RequestBody ChargerUpdateRequest chargerUpdateRequest){

        ChargerDto chargerDto = chargerService.chageCharger(chargerUpdateRequest);  //한번에 넣어도 되는지?

        return ResponseEntity.status(HttpStatus.OK).body(chargerDto);
    }

    //Charger 삭제
    @DeleteMapping()
    public  ResponseEntity<String> deleteCharger(Long number){
        chargerService.deleteCharger(number);

        return  ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }
}
