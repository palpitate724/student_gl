package com.example.jjwtukiter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


public class JwtUkiter {

//    @Value("${jwts.secret}")
//    public static String jwtSecret;
    public static final String jwtSecret = "f3+EaqqaZKJNC0yI/HM96scHrUbxqNhH4GWuxNXxGq0=";

    public static SecretKey jwtSignKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

//    //生成密钥
//    public SecretKey loginSignKey(){
//        return Jwts.SIG.HS256.key().build();
//    }


    public static String loginToken(String sid,String sname){

        SecretKey key = jwtSignKey();

        return Jwts.builder()
                .signWith(key)
                .claim("sid",sid)
                .claim("sname",sname)
                .notBefore(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600*1000))
                .compact();
    }


    public static boolean jiegouToken(String token){

        SecretKey key = jwtSignKey();

        try{
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
        }catch (JwtException ex){
            return false;
        }
        return true;
    }


}
