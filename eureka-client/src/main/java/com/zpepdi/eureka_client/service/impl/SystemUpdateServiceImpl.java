package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.SystemUpdateDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.SystemUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUpdateServiceImpl implements SystemUpdateService {
    @Autowired
    private SystemUpdateDao systemUpdateDao;

    @Override
    public Result query() {
        return Result.ok(systemUpdateDao.query());
    }
}
