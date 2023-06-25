package com.springboot.parkinglot.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

//    @Value("${springboot.jwt.secret}")
//    private String secretKey = "secretKey"; //test 를 위한 secretKey

//    @PostConstruct
//    protected void init() {
//
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
//    }

    public String createToken(String userUid, String roles) {

        //[createToken] 토큰 생성 시작
        //Claims claims = Jwts.claims().setSubject(userUid);
        //claims.put("roles", roles);

        //Date now = new Date();
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret 값 세팅
//                .compact();

        //token 생성 방식 변경
        String token = JWT.create()
                .withSubject(userUid)  // email 이용
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

        //[createToken] 토큰 생성 완료
        return token;
    }
}
