package com.springboot.parkinglot.service.login.impl;

import com.springboot.parkinglot.controller.favorite.Favorite;
import com.springboot.parkinglot.controller.login.LoginUser;
import com.springboot.parkinglot.controller.login.LoginUserDto;
import com.springboot.parkinglot.controller.login.SignInRequest;
import com.springboot.parkinglot.repository.favorite.FavoriteRepository;
import com.springboot.parkinglot.repository.login.IUserDao;
import com.springboot.parkinglot.service.login.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginUserServiceimpl implements LoginUserService {

    private final IUserDao iUserDao;

    @Autowired
    public LoginUserServiceimpl(IUserDao iUserDao,
                                FavoriteRepository favoriteRepository){
        this.iUserDao = iUserDao;
        this.favoriteRepository = favoriteRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final FavoriteRepository favoriteRepository;

    @Override
    public LoginUserDto getLoginUser(String username) {
        LoginUser loginUser = iUserDao.findByUsername(username).orElseThrow(
                ()->new RuntimeException("findByUsername not found"));

        //type casting : LoginUser -> LoginUserDto
        return LoginUserDto.builder()
                        .username(loginUser.getUsername())
                        .email(loginUser.getEmail())
                        .password(loginUser.getPassword())
                        .role(loginUser.getRole())
                        .active(loginUser.getActive())
                        .build();
    }

    @Override
    public List<LoginUserDto> getAllLoginUser() {
        List<LoginUser> listLoginUser = iUserDao.findAll();

        //type casting : LoginUser -> LoginUserDto
        //String username, String password, String email, String active, String role
        return listLoginUser.stream()
                .map(user -> new LoginUserDto(user.getUsername(), user.getPassword(),
                user.getEmail(), user.getActive(), user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public LoginUserDto signInLoginUser(SignInRequest signInRequest) {

        //중복성 체크
        this.iUserDao.findByEmail(signInRequest.getUserEmail())
                .ifPresent(
                m->{ throw new IllegalStateException("same email exists");}
        );

        LoginUser loginUser = LoginUser.builder()
                                .username(signInRequest.getUsername())
                                .email(signInRequest.getUserEmail())
                                .password(passwordEncoder.encode(signInRequest.getPassword()))
                                .role(signInRequest.getROLE())
                                .active("1")
                                .build();

        // Favorite 생성
        Favorite favorite = new Favorite();
        this.favoriteRepository.save(favorite);

        loginUser.setFavorite(favorite);

        //loginUser DB에 저장
        this.iUserDao.save(loginUser);

        //LoginUserDto에는 favorite가 없음
        return LoginUserDto.builder()
                .username(loginUser.getUsername())
                .email(loginUser.getEmail())
                .password(loginUser.getPassword())
                .role(loginUser.getRole())
                .active(loginUser.getActive())
                .build();
    }
}
