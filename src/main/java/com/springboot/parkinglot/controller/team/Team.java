package com.springboot.parkinglot.controller.team;


import com.springboot.parkinglot.controller.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", fetch=FetchType.EAGER)    //FetchType.EAGER 의미?
    private List<User> users = new ArrayList<User>();
}
