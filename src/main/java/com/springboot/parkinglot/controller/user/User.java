package com.springboot.parkinglot.controller.user;

import com.springboot.parkinglot.controller.team.Team;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="team_id")
    @ToString.Exclude       //ToString.Exclude의 역할? - 에러 삭제됨
    private Team team;
}
