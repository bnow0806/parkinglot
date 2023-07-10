package com.springboot.parkinglot.controller.favorite;

import com.springboot.parkinglot.controller.login.LoginUser;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter   //test
public class FavoriteDto {

    //Entity에 있는 것 다 담기
    private Long favoriteId;

    private LoginUser loginUser;

    private List<FavoriteCharger> favoriteCharger;

    @Builder
    public FavoriteDto(Long favoriteId, LoginUser loginUser, List<FavoriteCharger> favoriteCharger) {
        this.favoriteId =favoriteId;
        this.loginUser = loginUser;
        this.favoriteCharger = favoriteCharger;
    }
}
