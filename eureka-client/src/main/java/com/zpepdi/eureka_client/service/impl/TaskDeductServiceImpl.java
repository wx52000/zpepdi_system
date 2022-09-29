package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.TaskDeductDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.TaskDeductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class TaskDeductServiceImpl implements TaskDeductService {
    @Autowired
    private TaskDeductDao taskDeductDao;

    @Override
    public Result add(Integer id, Map<String, Object> map) {
        taskDeductDao.add(id,map);
        return Result.ok();
    }

    @Override
    public Result queryLog(Integer id) {
        return null;
    }

    @Override
    public Result queryLogById(Integer id) {
        return Result.ok(taskDeductDao.queryLogById(id));
    }
}
