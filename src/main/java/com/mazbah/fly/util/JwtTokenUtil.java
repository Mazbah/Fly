package com.mazbah.fly.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mazbah.fly.configs.ConfigProps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.validation.Validator;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtTokenUtil implements Serializable {

    private ConfigProps flyConfig;

    public Validator getVerifier(String token){
        return new Validator(token, flyConfig.getJwtSecret());
    }

    public Generator getGenerator(){
        return new Generator(flyConfig.getJwtSecret());
    }

    public class Validator {
        private String token;
        private static String secret;

        public Validator(String token, String secret) {
            this.token = token;
            this.secret = secret;
        }

        public String id() {
            return claim(token, Claims::getId);
        }

        public String subject() {
            return claim(token, Claims::getSubject);
        }

        public String issuer() {
            return claim(token, Claims::getIssuer);
        }

        public Date issueAt() {
            return claim(token, Claims::getIssuedAt);
        }

        public Date expiration() {
            return claim(token, Claims::getExpiration);
        }

        public String audience() {
            return claim(token, Claims::getAudience);
        }

        public Date notBefore() {
            return claim(token, Claims::getNotBefore);
        }

        public String userId(){
            claim(token);
            String userId = (String) claim.get("userId");
            return new StringBuilder(userId.trim()).reverse().toString();
        }

        private static <T> T claim(
                String token,
                Function<Claims, T> claimsResolver
        ) {
            final var claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        }

        private static Claims getAllClaimsFromToken(String token) {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }
    }

    class Generator {
        private String secret;

        public Generator(String secret) {
            this.secret = secret;
        }

        public String generate(String subject) {
            return generate(new HashMap<>(), subject);
        }

        public String generate(Map<String, Object> claims, String subject) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setAudience(flyConfig.getJwtAudience())
                    .setIssuer(flyConfig.getJwtTokenIssuer())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setNotBefore(new Date(System.currentTimeMillis()))
                    .setExpiration(expirationTime())
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        }
    }

    public Date expirationTime() {
        return new Date(System.currentTimeMillis() + flyConfig.getJwtTokenValidity() * 1000L);
    }

    public class JwtTokenClaims {
        private final String userId;

        public JwtTokenClaims(String userId) {
            this.userId = userId;
        }

        public Map<String, Object> toHash() {
            Map<String, Object> m = new HashMap<>();
            m.put("userId", userId);
            return m;
        }
    }
}
