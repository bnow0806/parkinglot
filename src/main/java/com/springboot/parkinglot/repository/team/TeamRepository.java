package com.springboot.parkinglot.repository.team;

import com.springboot.parkinglot.controller.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
