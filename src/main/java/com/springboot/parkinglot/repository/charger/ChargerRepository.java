package com.springboot.parkinglot.repository.charger;

import com.springboot.parkinglot.controller.charger.Charger;
import com.springboot.parkinglot.controller.charger.ChargerDto;
import com.springboot.parkinglot.controller.login.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChargerRepository extends JpaRepository<Charger, Long> {
    List<Charger> findAll();

    Optional<Charger> findByName(String name);
}
