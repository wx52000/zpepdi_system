package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ScientificDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScientificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScientificServiceImpl implements ScientificService {
    @Autowired
    private ScientificDao scientificDao;


    @Override
    public Result query() {
        return Result.ok(scientificDao.query());
    }

    @Override
    public Result queryById(Integer userId, Integer id) {
        return Result.ok(scientificDao.queryById(userId,id));
    }

    @Override
    public Result addScientific(Integer userId, Map<String, Object> map) {
        scientificDao.addScientific(userId,map);
        return Result.ok();
    }
}
