package com.springboot.parkinglot.service.favorite.impl;

import com.springboot.parkinglot.controller.charger.Charger;
import com.springboot.parkinglot.controller.favorite.Favorite;
import com.springboot.parkinglot.controller.favorite.FavoriteAddRequest;
import com.springboot.parkinglot.controller.favorite.FavoriteCharger;
import com.springboot.parkinglot.controller.favorite.FavoriteDto;
import com.springboot.parkinglot.controller.login.LoginUser;
import com.springboot.parkinglot.repository.charger.ChargerRepository;
import com.springboot.parkinglot.repository.favorite.FavoriteChargerRepository;
import com.springboot.parkinglot.repository.favorite.FavoriteRepository;
import com.springboot.parkinglot.repository.login.IUserDao;
import com.springboot.parkinglot.service.favorite.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final IUserDao iUserDao;

    private final FavoriteChargerRepository favoriteChargerRepository;
    private final ChargerRepository chargerRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, IUserDao iUserDao, FavoriteChargerRepository favoriteChargerRepository, ChargerRepository chargerRepository){
        this.favoriteRepository = favoriteRepository;
        this.iUserDao = iUserDao;
        this.favoriteChargerRepository = favoriteChargerRepository;
        this.chargerRepository = chargerRepository;
    }

    @Override
    public FavoriteDto addFavorite(FavoriteAddRequest favoriteAddRequest) {

        String username = favoriteAddRequest.getUsername();
        String chargername = favoriteAddRequest.getChargername();

        //loginuser의 username입력 -> favorite 찾기
        LoginUser loginUser = iUserDao.findByUsername(username).orElseThrow(
                ()->new RuntimeException("findByUsername not found"));

        Favorite favorite = favoriteRepository.findByLoginUser_Id(loginUser.getId()).get();

        //chargername 입력
        //favorite으로 Favoritecharger 찾아서 입력
        Long favoritePK = favorite.getId();

        List<FavoriteCharger> favoriteChargers;
        favoriteChargers = favoriteChargerRepository.findAllByFavorite(favoritePK);

        //출력
        for(FavoriteCharger favoriteCharger : favoriteChargers){
            System.out.println("Before addFavorite\n");
            System.out.println(favoriteCharger.getFavorite().getId()+","
                    + favoriteCharger.getCharger().getNumber());
        }

        //chargernumber 등록
        Charger charger = chargerRepository.findByName(chargername).get();

        //favoritrecharger 등록
        FavoriteCharger favoritCharger = new FavoriteCharger();
        favoritCharger.setFavorite(favorite);
        favoritCharger.setCharger(charger);
        favoritCharger.setFavoriteStatus(true);
        favoritCharger.setAlarmStatus(true);

        favorite.getFavoriteCharger().add(favoritCharger);

        //출력
        for(FavoriteCharger favoriteCharger : favoriteChargers){
            System.out.println("After addFavorite\n");
            System.out.println(favoriteCharger.getFavorite().getId()+","
                    + favoriteCharger.getCharger().getNumber());
        }

        return null;
    }

    @Override
    public FavoriteDto getFavorite() {
        return null;
    }

    @Override
    public FavoriteDto deleteFavorite() {
        return null;
    }
}
