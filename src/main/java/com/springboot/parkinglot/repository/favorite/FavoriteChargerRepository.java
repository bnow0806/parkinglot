package com.springboot.parkinglot.repository.favorite;

import com.springboot.parkinglot.controller.favorite.Favorite;
import com.springboot.parkinglot.controller.favorite.FavoriteCharger;
import com.springboot.parkinglot.controller.login.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteChargerRepository  extends JpaRepository<FavoriteCharger, Long> {

    List<FavoriteCharger> findAllByFavorite(Long id);
    List<FavoriteCharger> findByCharger_Number(Long number);
}


