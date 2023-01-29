package com.springboot.parkinglot.repository.user;

import com.springboot.parkinglot.controller.team.Team;
import com.springboot.parkinglot.controller.user.User;
import com.springboot.parkinglot.repository.team.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeamRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    void parkinglotTest1() {
       //test data
        Team team = new Team();
        team.setName("1팀");

        teamRepository.save(team);

        User user = new User();
        user.setName("Jim");
        user.setId("aaa");
        user.setPassword("aaa");
        user.setTeam(team);

        User user2 = new User();
        user2.setName("Jim2");
        user2.setId("bbb");
        user2.setPassword("bbb");
        user2.setTeam(team);

        userRepository.save(user);
        userRepository.save(user2);
        //test
        List<User> users = teamRepository.findById(team.getId()).get()
                .getUsers();

        for(User userTemp : users){
            System.out.println(userTemp);
        }
    }
}
