package com.mazbah.fly.service;

import com.mazbah.fly.dao.AuthUserDao;

public interface UserService {
    AuthUserDao getAuthUser(String userId);
}
