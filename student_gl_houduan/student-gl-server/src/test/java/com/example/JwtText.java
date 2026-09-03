package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JwtText {


    public List<Object> test1(){

        SecretKey key = Jwts.SIG.HS256.key().build();
        String jwt = Jwts.builder()
                         .signWith(key)
                         .notBefore(new Date())
                         .expiration(new Date(System.currentTimeMillis() + 3600*1000))
                          .compact();

        System.out.println(jwt);
        List<Object> jwtAndKey = new ArrayList<>();
        jwtAndKey.add(jwt);
        jwtAndKey.add(key);
        return jwtAndKey;
    }

    @Test
    public void test2(){
            List<Object> jwtAndKey = test1();
            String jwt = (String) jwtAndKey.get(0);
            SecretKey key = (SecretKey) jwtAndKey.get(1);
            Jws<Claims> cla = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jwt);
            System.out.println(cla.getPayload());
    }

    @Test
    void generateSafeKey() {
        SecretKey key = Jwts.SIG.HS256.key().build();
        System.out.println(Encoders.BASE64.encode(key.getEncoded()));
    }
}
