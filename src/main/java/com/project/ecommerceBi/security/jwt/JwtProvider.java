package com.project.ecommerceBi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        UserDetails mainUser = (UserDetails) authentication.getPrincipal();
        List<String> roles = mainUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());


        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withSubject(mainUser.getUsername())
                .withIssuedAt(new Date())
                .withClaim("roles", roles)
                .withExpiresAt(new Date(new Date().getTime() + expiration + 1000))
                .sign(algorithm);
        return token;


    }
    public String getUserNameFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
            //  Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (JWTVerificationException e) {
            logger.error("fail en la firma o en los claims");
        }
        return false;
    }

}
