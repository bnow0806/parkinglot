package com.springboot.parkinglot.service.charger.impl;

import com.springboot.parkinglot.controller.charger.Charger;
import com.springboot.parkinglot.controller.charger.ChargerCreateRequest;
import com.springboot.parkinglot.controller.charger.ChargerDto;
import com.springboot.parkinglot.controller.charger.ChargerUpdateRequest;
import com.springboot.parkinglot.repository.charger.ChargerRepository;
import com.springboot.parkinglot.service.charger.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChargerServiceimpl implements ChargerService { //TODO : need to implement

    private final ChargerRepository chargerRepository;

    @Autowired
    public ChargerServiceimpl(ChargerRepository chargerRepository)
    {
        this.chargerRepository = chargerRepository;

    }

    @Override
    public ChargerDto saveCharger(ChargerCreateRequest chargerCreateRequest) {

        Charger charger = Charger.builder()     //code 줄일 수 없을까?
                .location(chargerCreateRequest.getLocation())
                .name(chargerCreateRequest.getName())
                .paymentDetail(chargerCreateRequest.getPaymentDetail())
                .freeOfCharge(chargerCreateRequest.getFreeOfCharge())
                .voltage(chargerCreateRequest.getVoltage())
                .installType(chargerCreateRequest.getInstallType())
                .chargeType(chargerCreateRequest.getChargeType())
                .isUsable(chargerCreateRequest.getIsUsable())
                .cpo(chargerCreateRequest.getCpo())
                .absLocation(chargerCreateRequest.getAbsLocation())
                .paymentType(chargerCreateRequest.getPaymentType())
                .build();


        Charger savedCharger = chargerRepository.save(charger);

        return ChargerDto.builder()
                .location(savedCharger.getLocation())
                .name(savedCharger.getName())
                .paymentDetail(savedCharger.getPaymentDetail())
                .freeOfCharge(savedCharger.getFreeOfCharge())
                .voltage(savedCharger.getVoltage())
                .installType(savedCharger.getInstallType())
                .chargeType(savedCharger.getChargeType())
                .isUsable(savedCharger.getIsUsable())
                .cpo(savedCharger.getCpo())
                .absLocation(savedCharger.getAbsLocation())
                .paymentType(savedCharger.getPaymentType())
                .build();
    }

    @Override
    public ChargerDto getCharger(Long number) {
        Charger charger = chargerRepository.getById(number);

        return ChargerDto.builder()
                .location(charger.getLocation())
                .name(charger.getName())
                .paymentDetail(charger.getPaymentDetail())
                .freeOfCharge(charger.getFreeOfCharge())
                .voltage(charger.getVoltage())
                .installType(charger.getInstallType())
                .chargeType(charger.getChargeType())
                .isUsable(charger.getIsUsable())
                .cpo(charger.getCpo())
                .absLocation(charger.getAbsLocation())
                .paymentType(charger.getPaymentType())
                .build();
    }

    @Override
    public ChargerDto chageCharger(ChargerUpdateRequest chargerUpdateRequest) {

        Optional<Charger> selectedCharger = chargerRepository.findById(chargerUpdateRequest.getNumber());
        Charger updatedCharger = new Charger();

        if(selectedCharger.isPresent()){
            Charger charger = selectedCharger.get();

            charger.setIsUsable(chargerUpdateRequest.getIsUsable());

            updatedCharger = chargerRepository.save(charger);
        }


        return ChargerDto.builder()
                .location(updatedCharger.getLocation())
                .name(updatedCharger.getName())
                .paymentDetail(updatedCharger.getPaymentDetail())
                .freeOfCharge(updatedCharger.getFreeOfCharge())
                .voltage(updatedCharger.getVoltage())
                .installType(updatedCharger.getInstallType())
                .chargeType(updatedCharger.getChargeType())
                .isUsable(updatedCharger.getIsUsable())
                .cpo(updatedCharger.getCpo())
                .absLocation(updatedCharger.getAbsLocation())
                .paymentType(updatedCharger.getPaymentType())
                .build();
    }

    @Override
    public void deleteCharger(Long number) {
        Optional<Charger> selectedCharger = chargerRepository.findById(number);

        if (selectedCharger.isPresent()) {
            Charger charger = selectedCharger.get();

            chargerRepository.delete(charger);

        }
    }
}
