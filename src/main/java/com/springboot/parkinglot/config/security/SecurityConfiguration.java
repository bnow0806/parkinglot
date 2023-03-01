package com.springboot.parkinglot.config.security;


import com.springboot.parkinglot.repository.login.IUserDao;
import com.springboot.parkinglot.service.login.UserPrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 활성화
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserPrincipalDetailsService userPrincipalDetailsService;
    private final IUserDao iUserDao;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return  daoAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.iUserDao))
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "login").permitAll()
//                .antMatchers("/api/v1/login").hasRole("ADMIN")    // admin 만 접근 가능
//                .antMatchers("/api/v1/login2").hasRole("MANAGER") // manager 만 접근 가능
//                .anyRequest().authenticated(); // 이외에 모든 request는 로그인한 사용자만 접근 가능
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1/login/admin").hasRole("ADMIN") // 각 url에 접근 가능한지
                .antMatchers("/api/v1/login/manager").hasRole("MANAGER") // 확인하기 위해 설정 //TODO : Test 필요
                .antMatchers("/api/v1/alluserdata").hasRole("ADMIN")
                .antMatchers("/api/v1/myuserdata").hasRole("MANAGER")
                .antMatchers("/charger/admin").hasRole("ADMIN")
                .and()
                .httpBasic(); // 기본 로그인 창 제공

    }

    // Custom Security Config에서 사용할 password encoder를 BCryptPasswordEncoder로 정의
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
