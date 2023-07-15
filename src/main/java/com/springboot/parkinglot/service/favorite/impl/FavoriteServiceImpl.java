package com.springboot.parkinglot.service.favorite.impl;

import com.springboot.parkinglot.controller.charger.Charger;
import com.springboot.parkinglot.controller.charger.ChargerDto;
import com.springboot.parkinglot.controller.favorite.*;
import com.springboot.parkinglot.controller.login.LoginUser;
import com.springboot.parkinglot.repository.charger.ChargerRepository;
import com.springboot.parkinglot.repository.favorite.FavoriteChargerRepository;
import com.springboot.parkinglot.repository.favorite.FavoriteRepository;
import com.springboot.parkinglot.repository.login.IUserDao;
import com.springboot.parkinglot.service.favorite.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
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
        HashSet<Long> beforeChargers = new HashSet<>(); //username으로 조회되는 기존의 Chargers

        //입력 인자
        //loginuser의 username입력 -> favorite 찾기
        LoginUser loginUser = iUserDao.findByUsername(username).orElseThrow(
                ()->new RuntimeException("findByUsername not found"));

        Favorite favorite = favoriteRepository.findByLoginUser_Id(loginUser.getId()).get();

        //loginuser의 chargername -> charger 찾기
        Charger charger = chargerRepository.findByName(chargername).get();

        //1. FavoriteCharger가 비어있는 경우
        //2. Favorite Charger 쌍이 중복인 경우

        //Step1
        Long favoritePK = favorite.getId();
        List<FavoriteCharger> favoriteChargerTemps = favorite.getFavoriteCharger();     //Error arises

        if(favoriteChargerTemps == null){
            System.out.println("favoriteChargers is empty\n");
        }
        else{
            //기존 내용 출력
            for(FavoriteCharger favoriteChargerTemp : favoriteChargerTemps){
                System.out.println("Before addFavoriteCharger");
                System.out.println(favoriteChargerTemp.getFavorite().getId()+","
                        + favoriteChargerTemp.getCharger().getNumber()+"\n");
                //add
                beforeChargers.add(favoriteChargerTemp.getCharger().getNumber());
            }
        }

        //Step2
        //(favorite.getId() , charger.getNumber()) 쌍이 기존에 있는지 확인
        // (Long, Long)
        for (Long beforeCharger : beforeChargers) {
            System.out.println("Debug-beforeCharger");
            System.out.println(beforeCharger + "  ");
        }

        if(beforeChargers.contains(charger.getNumber())){
            System.out.println("beforeChargers.contains(charger.getNumber())");

            //바로 FavoriteDto를 반환
            return FavoriteDto.builder()
                    .favoriteId(favorite.getId())
                    .loginUser(loginUser)
                    .favoriteCharger(favorite.getFavoriteCharger())
                    .build();
        }
        else{
            //3. favoritecharger 저장 및 출력
            //Step3

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

            //성공했을 경우 FavoriteDto를 반환
            return FavoriteDto.builder()
                    .favoriteId(favorite.getId())
                    .loginUser(loginUser)
                    .favoriteCharger(favorite.getFavoriteCharger())
                    .build();
            //return null;
        }
    }

    @Override
    public FavoriteDto getFavorite(String username) {

        //1. username 받아서, favorite 조회
        //2. favorite 을 favoritedto에 담아서 return하면, charger 정보 모두 조회 가능

        //step1
        LoginUser loginUser = iUserDao.findByUsername(username).orElseThrow(
                ()->new RuntimeException("findByUsername not found"));

        Favorite favorite = favoriteRepository.findByLoginUser_Id(loginUser.getId()).get();

        //step2
        return FavoriteDto.builder()
                .favoriteId(favorite.getId())
                .loginUser(loginUser)
                .favoriteCharger(favorite.getFavoriteCharger())
                .build();
    }

    @Override
    public FavoriteDto deleteFavorite(FavoriteDeleteRequest favoriteDeleteRequest) {

        //1. username, chargername을 받음 -> 각각 id 로 변환
        //   username(user) -> favorite -> favoritecharger 접근
        //2. favorite 내 List<favortiecharger>에서 charger_id가 일치하는 것을 삭제
        //3. favoritecharger 중에서 charger_id가 일치하는 것을 모두 삭제, 저장

        //Step1
        String username = favoriteDeleteRequest.getUsername();
        String chargername = favoriteDeleteRequest.getChargername();
        //HashSet<Long> beforeChargers = new HashSet<>(); //username으로 조회되는 기존의 Chargers

        //입력 인자
        //loginuser의 username입력 -> favorite 찾기
        LoginUser loginUser = iUserDao.findByUsername(username).orElseThrow(
                ()->new RuntimeException("findByUsername not found"));

        Favorite favorite = favoriteRepository.findByLoginUser_Id(loginUser.getId()).get();

        //loginuser의 chargername -> charger 찾기
        Charger charger = chargerRepository.findByName(chargername).get();
        Long chargerNumber = charger.getNumber();

        //Step2
        List<FavoriteCharger> favoriteChargerFromFavorite = favorite.getFavoriteCharger();

        System.out.println("Before favorite.getFavoriteCharger()\n"+favoriteChargerFromFavorite);

        //Iterator를 이용해 List 내 요소 제거
        Iterator<FavoriteCharger> iterator = favoriteChargerFromFavorite.iterator();
        while (iterator.hasNext()) {
            FavoriteCharger favoriteChargerTemp = iterator.next();
            if (chargerNumber.equals(favoriteChargerTemp.getCharger().getNumber())) {
                iterator.remove();
            }
        }

        //System.out.println("Removed favoriteChargerFromFavorite\n"+favoriteChargerFromFavorite);

        favorite.setFavoriteCharger(favoriteChargerFromFavorite);   //덮어쓰기?
        Favorite updatedfavorite = favoriteRepository.save(favorite);  //삭제 후 저장

        //저장이 되지 않고, 재실행 시 Before favorite.getFavoriteCharger()가 동일하게 표현됨   //해결 필요
        System.out.println("Updated favorite.getFavoriteCharger()\n"+updatedfavorite.getFavoriteCharger());


        //Step3 //Step3를 실시하면 Step2는 저절로 실시됨
        List<FavoriteCharger> favoriteChargerFromCharger = favoriteChargerRepository.findByCharger_Number(chargerNumber);

        //기존 내용 출력
        for(FavoriteCharger favoriteChargerTemp : favoriteChargerFromCharger){
            System.out.println("Before deleteFavoriteCharger");
            System.out.println("favoriteChargerId : "+favoriteChargerTemp.getId()+" ("
                    +favoriteChargerTemp.getFavorite().getId()+","
                    + favoriteChargerTemp.getCharger().getNumber()+")\n");
            //delete    //Step3를 실시하면 Step2는 저절로 실시됨
            //favoriteChargerRepository.delete(favoriteChargerTemp);
        }

        //return null;
        return FavoriteDto.builder()
               .favoriteId(favorite.getId())
                .loginUser(loginUser)
                .favoriteCharger(favorite.getFavoriteCharger())
                .build();
    }
}
