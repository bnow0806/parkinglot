package com.springboot.parkinglot.controller.charger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@Data
public class ChargerDto {

    private Long number;

    private String location;

    private String name;

    private String paymentDetail;

    private int freeOfCharge;

    private String voltage;

    private String installType;

    private String chargeType;

    private String isUsable;

    private String cpo;

    private String absLocation;

    private String paymentType;

    @Builder
    public ChargerDto(Long number, String location, String name, String paymentDetail, int freeOfCharge, String voltage, String installType, String chargeType, String isUsable, String cpo, String absLocation, String paymentType) {
        this.number = number;
        this.location = location;
        this.name = name;
        this.paymentDetail = paymentDetail;
        this.freeOfCharge = freeOfCharge;
        this.voltage = voltage;
        this.installType = installType;
        this.chargeType = chargeType;
        this.isUsable = isUsable;
        this.cpo = cpo;
        this.absLocation = absLocation;
        this.paymentType = paymentType;
    }
}
