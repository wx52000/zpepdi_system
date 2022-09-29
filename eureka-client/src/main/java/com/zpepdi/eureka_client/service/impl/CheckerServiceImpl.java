package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.CheckerDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.CheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class CheckerServiceImpl implements CheckerService {
    @Autowired
    private CheckerDao checkerDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Result del(Map<String, Object> map) {
        String listName;
        if (map.get("type").toString().equals("0")) {
            listName = "headmanList";
        }else {
            listName = "directorList";
        }
        redisTemplate.delete(listName);
        checkerDao.del(map);
        return Result.ok();
    }

    @Override
    public Result set(Map<String, Object> map) {
        String listName;
        if (map.get("type").toString().equals("0")) {
            listName = "headmanList";
        }else {
            listName = "directorList";
        }
        redisTemplate.delete(listName);
        checkerDao.set(map);
        return Result.ok();
    }

    @Override
    public Result queryDepChecker() {
        return Result.ok(checkerDao.queryDepChecker());
    }

    @Override
    public Result delDepChecker(Map<String, Object> map) {
        checkerDao.delDepChecker(map);
        return null;
    }

    @Override
    public Result setDepChecker(Map<String, Object> map) {
        checkerDao.setDepChecker(map);
        checkerDao.setCheckerAmount(map);
        return null;
    }

}
