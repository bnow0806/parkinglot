package com.springboot.parkinglot.controller.charger;

import com.springboot.parkinglot.controller.team.Team;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name="charger")
@NoArgsConstructor
public class Charger {  //data 묶을수 없을까?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String paymentDetail;

    @Column(nullable = false)
    private int freeOfCharge;

    @Column(nullable = false)
    private String voltage;

    @Column(nullable = false)
    private String installType;

    @Column(nullable = false)
    private String chargeType;

    @Column(nullable = false)
    private String isUsable;

    @Column(nullable = false)
    private String cpo;

    @Column(nullable = false)
    private String absLocation;

    @Column(nullable = false)
    private String paymentType;

    //TODO : 연관관계 매핑 데이터    //비독립적인 데이터
    //private String adapterType; //enum 같이?

    //private String chargeFee;

    @Builder
    public Charger(Long number, String location, String name, String paymentDetail, int freeOfCharge, String voltage, String installType, String chargeType, String isUsable, String cpo, String absLocation, String paymentType) {
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
