package com.example.securitytokken.user;

import com.example.securitytokken.authentication.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManagerBuilder authMngBuilder;

    @PostMapping("/join")
    public Map join(UserDto userDto) {
        Map map = new HashMap();
        boolean flag = true;
        try {
            userService.saveUser(userDto);
        } catch (Exception e) {
            flag = false;
            System.out.println(e);
        }
        map.put("flag", flag);
        return map;
    }

    @PostMapping("/login")
    public Map login(String id, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, password);
        Authentication authentication = authMngBuilder.getObject().authenticate(authToken);
        boolean flag = authentication.isAuthenticated(); //인증 결과
        System.out.println("인증 결과: " + flag);

        Map map = new HashMap();
        if (flag) {
            String token = tokenProvider.getToken(userService.getUser(id));
            map.put("token", token);
        }
        map.put("flag", flag);
        return map;
    }

    @GetMapping("/auth/userinfo")
    public Map memberinfo() {
        Map map = new HashMap();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName(); //username 추출
        UserDto userDto = userService.getUser(id);
        map.put("userDto", userDto);
        return map;
    }
}
