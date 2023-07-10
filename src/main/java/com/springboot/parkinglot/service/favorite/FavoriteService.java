package com.springboot.parkinglot.service.favorite;

import com.springboot.parkinglot.controller.favorite.FavoriteAddRequest;
import com.springboot.parkinglot.controller.favorite.FavoriteDeleteRequest;
import com.springboot.parkinglot.controller.favorite.FavoriteDto;

public interface FavoriteService {

    FavoriteDto addFavorite(FavoriteAddRequest favoriteAddRequest);

    FavoriteDto getFavorite(String username);

    FavoriteDto deleteFavorite(FavoriteDeleteRequest favoriteDeleteRequest);
}
