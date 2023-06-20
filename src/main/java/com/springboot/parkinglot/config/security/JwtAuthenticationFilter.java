package com.springboot.parkinglot.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.parkinglot.controller.login.LoginViewModel;
import com.springboot.parkinglot.controller.login.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        LoginViewModel credentials = null;
        try {
            // TODO : Bean으로.. @Autowired
            // ObjectMapper 라이브러리를 이용하여 JSON -> Object 변환 (readValue)
            // request body 데이터 확인 request.getInputStream()
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
        } catch(IOException e) {    // TODO : throw
            e.printStackTrace();
        }

        // Create login Token
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        credentials.getUserEmail(),    // email 이용
                        credentials.getPassword(),
                        new ArrayList<>()
                );
        // Authentication User !
       Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // Grab principal
        UserPrincipal principal = (UserPrincipal)authResult.getPrincipal();

        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getEmail())  // email 이용
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes())); // signature
        // Add Token in response header
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
    }
}
