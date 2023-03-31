package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ScientificTaskDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScientificTaskService;
import com.zpepdi.eureka_client.tools.TaskNumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ScientificTaskServiceImpl implements ScientificTaskService {
    @Autowired
    private ScientificTaskDao scientificTaskDao;

    @Override
    public Result querySurplus(Integer userId, Integer id) {
        Map<String,Object> map = scientificTaskDao.querySurplus(userId,id);
        map.put("number", TaskNumberUtils.scientificNumber(map.get("number").toString()));
        return Result.ok(map);
    }

    @Override
    @Transactional
    public Result addTask(Integer userId, Map<String, Object> map) {
        scientificTaskDao.addTask(userId,map);
        scientificTaskDao.addTaskWorkday(map);
        return Result.ok();
    }


    @Override
    public Result queryTask(Integer userId, Integer id) {
        return Result.ok(scientificTaskDao.queryTask(userId,id));
    }

    @Override
    public Result getCheckerList( Integer id) {
        return Result.ok(scientificTaskDao.getCheckerList(id));
    }

    @Override
    public Result queryTaskBySubmitDate(Integer userId, Map<String, Object> map) {
        return Result.ok(scientificTaskDao.queryTaskBySubmitDate(userId,map));
    }

    @Override
    public Result taskSubmit(Map<String, Object> map) {
        scientificTaskDao.taskSubmit(map);
        return Result.ok();
    }
}
