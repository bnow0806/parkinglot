package com.springboot.parkinglot.controller.login;

import com.springboot.parkinglot.controller.user.UserDto;
import com.springboot.parkinglot.repository.login.IUserDao;
import com.springboot.parkinglot.service.login.LoginUserService;
import com.springboot.parkinglot.service.login.UserPrincipalDetailsService;
import com.springboot.parkinglot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class LoginController {

    private  final LoginUserService loginUserService;

    @Autowired
    public LoginController(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @GetMapping("/api/v1/login/admin") // admin 권한에게만
    public String loginAdmin() {

        return "Success ADMIN";
    }

    @GetMapping("/api/v1/login/manager") // manager 권한에게만
    public String loginManager() {

        return "Success MANAGER";
    }

    @GetMapping("/api/v1/alluserdata") //get all user data
    public ResponseEntity< List<LoginUserDto> > allUserData(){
        List<LoginUserDto> listLoginUserDto = loginUserService.getAllLoginUser();

        return ResponseEntity.status(HttpStatus.OK).body(listLoginUserDto);
    }

    @GetMapping("/api/v1/myuserdata")   //get my user data
    public ResponseEntity<LoginUserDto> myUserData(String username){
        LoginUserDto loginUserDto = loginUserService.getLoginUser(username);

        return ResponseEntity.status(HttpStatus.OK).body(loginUserDto);
    }

    @GetMapping("api/v1/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        //new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        //HttpSession session=request.getSession(false);
        //session.invalidate();
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie : cookies)
        {
            System.out.println("cookie" + cookie);
        }

        return "redirect:/";
    }
}
