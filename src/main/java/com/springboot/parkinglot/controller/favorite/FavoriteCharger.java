package com.springboot.parkinglot.controller.favorite;

import com.springboot.parkinglot.controller.charger.Charger;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="favorite_charger")
public class FavoriteCharger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude                   //java.lang.StackOverflowError
    @JoinColumn(name = "favorite_id")   //FK를 가짐
    private Favorite favorite;

    @ManyToOne
    @ToString.Exclude                   //java.lang.StackOverflowError
    @JoinColumn(name = "charger_id")    //FK를 가짐
    private Charger charger;

    //추가 정보
    private  boolean favoriteStatus;

    private boolean alarmStatus;
}

