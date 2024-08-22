package com.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.time}")
    private int expiryTime;
private Algorithm algorithm;
private static final String  USER_NAME="username";
@PostConstruct
    public void PostConstruct() throws UnsupportedEncodingException {
    algorithm=Algorithm.HMAC256(algorithmKey);
}
public String generateToken(AppUser appUser){//generate token

    return JWT.create()//computer engineer is sigma ~~learn shortcut
            .withClaim(USER_NAME,appUser.getUsername()).
            withExpiresAt(new Date(System.currentTimeMillis()+expiryTime)).
            withIssuer(issuer)
            .sign(algorithm);

}
public String getUserName(String token){//verify token wth username
   DecodedJWT decodedJWT = JWT.require(algorithm).//jockey rockey with bodybuilder vikram ~~ shortcut
            withIssuer(issuer).
            build().
            verify(token);
   return decodedJWT.getClaim(USER_NAME).asString();//return username
}


}
