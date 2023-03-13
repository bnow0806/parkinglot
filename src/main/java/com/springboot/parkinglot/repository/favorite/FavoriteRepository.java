package com.springboot.parkinglot.repository.favorite;

import com.springboot.parkinglot.controller.favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository  extends JpaRepository<Favorite, Long> {
}
