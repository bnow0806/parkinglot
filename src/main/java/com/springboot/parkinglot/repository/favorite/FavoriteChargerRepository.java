package com.springboot.parkinglot.repository.favorite;

import com.springboot.parkinglot.controller.favorite.FavoriteCharger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteChargerRepository  extends JpaRepository<FavoriteCharger, Long> {
}
