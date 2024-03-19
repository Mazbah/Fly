package com.mazbah.fly.service.impl;

import com.mazbah.fly.dao.AuthUserDao;
import com.mazbah.fly.repository.UserRepository;
import com.mazbah.fly.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public AuthUserDao getAuthUser(String userId) {
        String tokenHash = repository.findByTokenHash(userId);
        if(tokenHash == null) tokenHash = "";
        return new AuthUserDao(tokenHash,userId);
    }
}
