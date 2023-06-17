package com.springboot.parkinglot.controller.favorite;

import com.springboot.parkinglot.controller.login.LoginUser;

import java.util.List;

public class FavoriteDto {

    //Entity에 있는 것 다 담기
    private Long FavoriteId;

    private LoginUser loginUser;

    private List<FavoriteCharger> favoriteCharger;

}
