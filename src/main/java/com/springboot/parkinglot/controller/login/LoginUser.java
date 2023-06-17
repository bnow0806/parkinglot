package com.springboot.parkinglot.controller.login;

import com.springboot.parkinglot.controller.favorite.Favorite;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.persistence.*;

@Entity //added because of "Not a managed type:"
@Getter
@Setter
@NoArgsConstructor
@Table(name="login_user")
public class LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity to auto
    private Long id;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;  // user를 찾을 때 email을 이용하여 찾는다. //why??

    @Column(nullable = false)
    private String active; // 1: active, 0: inactive

    @Column(nullable = false)
    private String role;

    @OneToOne
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;

    @Builder
    public LoginUser(String username, String password, String email, String active, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.role = role;
    }
}
