package com.springboot.parkinglot.repository.favorite;

import com.springboot.parkinglot.controller.favorite.Favorite;
import com.springboot.parkinglot.controller.login.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository  extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByLoginUser_Id(Long id);
}
