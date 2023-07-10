package com.springboot.parkinglot.controller.favorite;

import com.springboot.parkinglot.service.favorite.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//검색 & 등록 & 삭제 기능 구현 필요
// 등록 -> 검색 -> 삭제순 구현
// 현재 : 검색, 삭제 구현 필요
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    @PostMapping()
    public ResponseEntity<FavoriteDto> addFavorite(FavoriteAddRequest favoriteAddRequest){

        FavoriteDto favoriteDto = favoriteService.addFavorite(favoriteAddRequest);

        return ResponseEntity.status(HttpStatus.OK).body(favoriteDto);
    }

    @GetMapping()
    public ResponseEntity<FavoriteDto> getFavorite(String username){

        FavoriteDto favoriteDto = favoriteService.getFavorite(username);

        return ResponseEntity.status(HttpStatus.OK).body(favoriteDto);
    }

    @DeleteMapping()
    public ResponseEntity<FavoriteDto> deleteFavorite(FavoriteDeleteRequest favoriteDeleteRequest){

        FavoriteDto favoriteDto = favoriteService.deleteFavorite(favoriteDeleteRequest);

        return ResponseEntity.status(HttpStatus.OK).body(favoriteDto);
    }

}
