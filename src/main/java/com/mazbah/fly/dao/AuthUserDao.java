package com.mazbah.fly.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDao {
    String token;
    String userId;

    public AuthUserDao(String token) {
        this.token = token;
    }

    public AuthUserDao(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }
}
