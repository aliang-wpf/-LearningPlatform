package com.wpf.controller;

import com.wpf.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Slf4j
@RestController
public class SessionController {


    //设置cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response){
        response.addCookie(new Cookie("username","wpf"));
        return Result.success();
    }

    //获取cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Arrays.stream(cookies).filter(s->"username"
                            .equals(s.getName())&&"wpf"
                            .equals(s.getValue())).forEach(s-> System.out.println("username"+s.getValue()));
        return Result.success();
    }

}
