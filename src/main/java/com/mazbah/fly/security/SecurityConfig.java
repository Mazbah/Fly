package com.mazbah.fly.security;

import com.mazbah.fly.configs.ConfigProps;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.validation.Validator;
import java.io.Serializable;

public class SecurityConfig implements Serializable {

    @Autowired ConfigProps config;

//    public Validator getVerifier(String token){
//         Validator v = new Validator(token, config.getJwtSecret());
//    }
//
//    fun getVerifier(token: String): Validator = Validator(token, fsConfig.jwtSecret)

}
