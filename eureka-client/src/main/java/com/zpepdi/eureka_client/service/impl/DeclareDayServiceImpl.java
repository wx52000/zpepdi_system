package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.DeclareDayDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.DeclareDayService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeclareDayServiceImpl implements DeclareDayService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private DeclareDayDao declareDayDao;
    @Override
    public Integer declareDay() {
        Object object = redisTemplate.opsForValue().get("declareDay");
        if (object == null){
            object = declareDayDao.declareDay();
            redisTemplate.opsForValue().set("declareDay",object);
        }
        return Integer.valueOf(object.toString());
    }

    @Override
    public Integer declareDelay() {
        Object object = redisTemplate.opsForValue().get("declareDelay");
        if (object == null){
            object = declareDayDao.declareDelay();
            redisTemplate.opsForValue().set("declareDelay",object);
        }
        return Integer.valueOf(object.toString());
    }

    @Override
    @Transactional
    public Result setDeclareDay(Integer day) {
        declareDayDao.setDeclareDay(day);
        redisTemplate.opsForValue().set("declareDay",day);
        return Result.ok();
    }

    @Override
    @Transactional
    public Result setDeclareDelay(Integer day) {
        declareDayDao.setDeclareDelay(day);
        redisTemplate.opsForValue().set("declareDelay",day);
        return Result.ok();
    }
}
