package com.springboot.parkinglot.controller.charger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
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
}
