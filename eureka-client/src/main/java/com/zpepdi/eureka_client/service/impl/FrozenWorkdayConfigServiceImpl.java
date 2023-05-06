package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.FrozenWorkdayConfigDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.FrozenWorkdayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FrozenWorkdayConfigServiceImpl implements FrozenWorkdayConfigService {
    @Autowired
    private FrozenWorkdayConfigDao frozenWorkdayConfigDao;

    @Override
    public Result query() {
        return Result.ok(frozenWorkdayConfigDao.query());
    }

    @Override
    public Result setProjectFrozen(Map<String, Object> map) {
        frozenWorkdayConfigDao.setProjectFrozen(map);
        return Result.ok();
    }

    @Override
    public Result setFrozenNumber(Integer userId, Map<String, Object> map) {
        frozenWorkdayConfigDao.setFrozenNumber(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryByUserAndProject(Integer userId, Integer projectId) {
        return Result.ok(frozenWorkdayConfigDao.queryByUserAndProject(userId,projectId));
    }

    @Override
    public Result queryByUserId(Integer userId) {
        return Result.ok(frozenWorkdayConfigDao.queryByUserId(userId));
    }
}
