package com.zpepdi.oauth_server.service;

import com.zpepdi.oauth_server.entity.User;

import java.util.HashMap;
import java.util.Map;

public interface UserService{
    User user(String username);

}
