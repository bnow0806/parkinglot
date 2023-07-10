package com.springboot.parkinglot.controller.favorite;

import lombok.Data;

@Data
public class FavoriteDeleteRequest {
    private String username;
    private String chargername;
}
