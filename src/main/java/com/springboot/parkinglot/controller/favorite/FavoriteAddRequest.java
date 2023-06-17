package com.springboot.parkinglot.controller.favorite;

import lombok.Data;

@Data
public class FavoriteAddRequest {
    private String username;
    private String chargername;
}
