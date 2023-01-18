package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.UserDefinedTopDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.UserDefinedTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserDefinedTopServiceImpl implements UserDefinedTopService {
    @Autowired
    private UserDefinedTopDao userDefinedTopDao;

    @Override
    public Result setUserDefinedTop(Integer userId, Map<String, Object> map) {
        userDefinedTopDao.setUserDefinedTop(userId,map);
        return Result.ok();
    }

    @Override
    public Result resetUserDefinedTop(Integer userId, Map<String, Object> map) {
        userDefinedTopDao.resetUserDefinedTop(userId,map);
        return Result.ok();
    }
}
