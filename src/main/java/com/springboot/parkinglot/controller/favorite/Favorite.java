package com.springboot.parkinglot.controller.favorite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.parkinglot.controller.login.LoginUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "favorite")
//@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "favorite")    //1대1 양방향 매핑
    private LoginUser loginUser;

    //역방향
    @JsonIgnore
    @OneToMany(mappedBy = "favorite", fetch=FetchType.LAZY, orphanRemoval = true)    //lazy //orhanRemoval 추가
    private List<FavoriteCharger> favoriteCharger;

    public Favorite() {             //생성자 강제로 만들기
        this.favoriteCharger = new ArrayList<>();
    }
}