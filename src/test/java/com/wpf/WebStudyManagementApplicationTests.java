package com.wpf;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class WebStudyManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    //@Test
    void getJwt(){

        Map<String,Object>pass = new HashMap<>();
        pass.put("id",1);
        pass.put("username","aaa");
        String jwt = Jwts.builder()
                .setClaims(pass)//自定义内容
                .signWith(SignatureAlgorithm.HS256, "kabuto")//签名算法
                .setExpiration(new Date(System.currentTimeMillis() + 24*3600 * 1000))//设置有效期
                .compact();
        System.out.println(jwt);
    }
    //@Test
    public void genJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","Tom");

        String secretKey = "wpf";
        byte[] secretBytes = secretKey.getBytes(StandardCharsets.UTF_8);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretBytes)
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 1000))
                .compact();

        System.out.println(jwt);
    }
    //@Test
    public void parseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("wpf")//指定签名密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjg5MTYzNTM4LCJ1c2VybmFtZSI6IndwZiJ9.EQyepuQIMGcSvIvEAcO_fJ2Ho_gcjZl3iwuhwqKcX6Y")
                .getBody();

        System.out.println(claims);
    }
    //@Test
    public void parseJwt1(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")//指定签名密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjg5MTYzNjc3LCJ1c2VybmFtZSI6IlRvbSJ9.sx1Usp1_-a2nDMskU3_iRX06T8ukhiFwP94E_c07tL4")
                .getBody();

        System.out.println(claims);
    }

}
