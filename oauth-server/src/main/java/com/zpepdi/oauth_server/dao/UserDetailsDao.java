package com.zpepdi.oauth_server.dao;

import com.zpepdi.oauth_server.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface UserDetailsDao {

    User user(String username);
}
