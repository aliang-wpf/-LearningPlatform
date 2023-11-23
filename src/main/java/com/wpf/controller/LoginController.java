package com.wpf.controller;

import com.wpf.pojo.Emp;
import com.wpf.pojo.Result;
import com.wpf.service.EmpService;
import com.wpf.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
@Slf4j
@RestController
public class LoginController {
        @Autowired
        private EmpService empService;


    @PostMapping ("/login")
    public Result login(@RequestBody Emp emp){
        //调用业务层：登录功能
        Emp loginEmp = empService.login(emp);
        //如果登陆成功
        if(loginEmp!=null){
            //自定义信息
            Map<String, Object> claims =new HashMap<>();
            claims.put("id",loginEmp.getId());
            claims.put("username",loginEmp.getUsername());
            claims.put("name",loginEmp.getName());
            //使用JWT工具类，生成身份令牌
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return  Result.error("登录信息有误");
    }
}
*/
@RestController
@Slf4j
public class LoginController {
    //依赖业务层对象
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        //调用业务层：登录功能
        Emp loginEmp = empService.login(emp);

        //判断：登录用户是否存在
        if(loginEmp !=null ){
            //自定义信息
            Map<String , Object> claims = new HashMap<>();
            claims.put("id", loginEmp.getId());
            claims.put("username",loginEmp.getUsername());
            claims.put("name",loginEmp.getName());

            //使用JWT工具类，生成身份令牌
            String token = JwtUtils.generateJwt(claims);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }
}
