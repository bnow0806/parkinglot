package com.springboot.parkinglot.repository.favorite;

import com.springboot.parkinglot.controller.favorite.FavoriteCharger;
import com.springboot.parkinglot.controller.login.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteChargerRepository  extends JpaRepository<FavoriteCharger, Long> {

    List<FavoriteCharger> findAllByFavorite(Long id);
}


