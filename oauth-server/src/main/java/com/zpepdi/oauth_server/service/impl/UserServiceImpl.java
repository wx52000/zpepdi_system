package com.zpepdi.oauth_server.service.impl;

import com.zpepdi.oauth_server.dao.UserDetailsDao;
import com.zpepdi.oauth_server.entity.User;
import com.zpepdi.oauth_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDetailsDao detailsDao;

    @Override
    public User user(String username) {
        return detailsDao.user(username);
    }
}
