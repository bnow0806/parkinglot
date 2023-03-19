package com.springboot.parkinglot.repository.favorite;


import com.springboot.parkinglot.controller.charger.Charger;
import com.springboot.parkinglot.controller.favorite.Favorite;
import com.springboot.parkinglot.controller.favorite.FavoriteCharger;
import com.springboot.parkinglot.repository.charger.ChargerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class FavoriteRepositoryTest {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    ChargerRepository chargerRepository;

    @Autowired
    FavoriteChargerRepository favoriteChargerRepository;

    @Test
    void parkinglotTest2() {

        //Favorite - FavoriteCharger - Charger
        Favorite favoriteFirst = new Favorite();
        favoriteRepository.save(favoriteFirst);

        Charger chargerFirst = Charger.builder()
                                .location("(10,20)")
                                .name("TestChargerFirst")
                                .paymentDetail("EIM")
                                .freeOfCharge(1)
                                .voltage("400")
                                .installType("move")
                                .chargeType("ccs1")
                                .isUsable("usable")
                                .cpo("kepco")
                                .absLocation("(10,20)")
                                .paymentType("EIM")
                                .build();

        Charger chargerSecond = Charger.builder()
                                .location("(10,20)")
                                .name("TestChargerSecond")
                                .paymentDetail("EIM")
                                .freeOfCharge(1)
                                .voltage("400")
                                .installType("move")
                                .chargeType("ccs1")
                                .isUsable("usable")
                                .cpo("kepco2")
                                .absLocation("(10,20)")
                                .paymentType("EIM")
                                .build();

        chargerRepository.save(chargerFirst);
        chargerRepository.save(chargerSecond);

        FavoriteCharger favoriteChargerFirst = new FavoriteCharger();
        favoriteChargerFirst.setFavorite(favoriteFirst);
        favoriteChargerFirst.setCharger(chargerFirst);
        favoriteChargerFirst.setFavoriteStatus(true);
        favoriteChargerFirst.setAlarmStatus(true);

        FavoriteCharger favoritChargerSecond = new FavoriteCharger();
        favoritChargerSecond.setFavorite(favoriteFirst);
        favoritChargerSecond.setCharger(chargerSecond);
        favoritChargerSecond.setFavoriteStatus(true);
        favoritChargerSecond.setAlarmStatus(true);

        favoriteChargerRepository.save(favoriteChargerFirst);
        favoriteChargerRepository.save(favoritChargerSecond);

        favoriteFirst.getFavoriteCharger().add(favoriteChargerFirst);
        favoriteFirst.getFavoriteCharger().add(favoritChargerSecond);
        favoriteRepository.save(favoriteFirst);     //한번 더 저장

        Favorite savedFavorite = favoriteRepository.findById(favoriteFirst.getId()).get();

        //Test
        System.out.println("savedFavorite " + savedFavorite);   //error occurs

        List<FavoriteCharger> savedFavoriteFavoriteCharger = savedFavorite.getFavoriteCharger();
        for(FavoriteCharger favoriteChargerTemp : savedFavoriteFavoriteCharger){
            System.out.println("favoriteChargerTemp " + favoriteChargerTemp);
            System.out.println("favoriteChargerTemp.getCharger() " + favoriteChargerTemp.getCharger());
            System.out.println("getCpo " + favoriteChargerTemp.getCharger().getCpo());
        }


    }
}


//LoginUser.builder()
//        .username("admin")
//        .email(testEmail)
//        .password(passwordEncoder.encode("admin"))
//        .role("ROLE_ADMIN")
//        .active("1")
//        .build());