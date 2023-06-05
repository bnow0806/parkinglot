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

import java.util.HashSet;
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
        HashSet<Long> beforeChargers = new HashSet<>();

        //입력 인자
        //loginuser의 username입력 -> favorite 찾기
        LoginUser loginUser = iUserDao.findByUsername(username).orElseThrow(
                ()->new RuntimeException("findByUsername not found"));

        Favorite favorite = favoriteRepository.findByLoginUser_Id(loginUser.getId()).get();

        //loginuser의 chargername -> charger 찾기
        Charger charger = chargerRepository.findByName(chargername).get();

        //1. FavoriteCharger가 비어있는 경우
        //2. Favorite Charger 쌍이 중복인 경우

        //Step1.
        Long favoritePK = favorite.getId();
        List<FavoriteCharger> favoriteChargerTemps = favorite.getFavoriteCharger();     //Error arises

        if(favoriteChargerTemps == null){
            System.out.println("favoriteChargers is empty\n");
        }
        else{
            //기존 내용 출력
            for(FavoriteCharger favoriteChargerTemp : favoriteChargerTemps){
                System.out.println("Before addFavoriteCharger\n");
                System.out.println(favoriteChargerTemp.getFavorite().getId()+","
                        + favoriteChargerTemp.getCharger().getNumber());
                //add
                beforeChargers.add(favoriteChargerTemp.getCharger().getNumber());
            }
        }

        //Step2.
        //(favorite.getId() , charger.getNumber()) 쌍이 기존에 있는지 확인
        // (Long, Long)
        for (Long beforeCharger : beforeChargers) {
            System.out.println("Debug-beforeCharger");
            System.out.println(beforeCharger + "  ");
        }

        if(beforeChargers.contains(charger.getNumber())){
            return null;
        }
        else{


            //3. favoritecharger 저장 및 출력
            //Step3.

            //favoritrecharger 등록
            FavoriteCharger favoritCharger = new FavoriteCharger();
            favoritCharger.setFavorite(favorite);
            favoritCharger.setCharger(charger);
            favoritCharger.setFavoriteStatus(true);
            favoritCharger.setAlarmStatus(true);

            favoriteChargerRepository.save(favoritCharger);

            favorite.getFavoriteCharger().add(favoritCharger);
            favoriteRepository.save(favorite);  //한번 더 저장

            List<FavoriteCharger> savedFavoriteFavoriteChargers = favorite.getFavoriteCharger();
            //favoriteChargers = favoriteChargerRepository.findAllByFavorite(favoritePK);
            //출력
            for (FavoriteCharger savedFavoriteFavoriteCharger : savedFavoriteFavoriteChargers) {
                System.out.println("After addFavoriteCharger\n");
                System.out.println(savedFavoriteFavoriteCharger.getFavorite().getId() + ","
                        + savedFavoriteFavoriteCharger.getCharger().getNumber());
            }

            return null;
        }
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
