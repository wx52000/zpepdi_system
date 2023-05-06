package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ProjectRelativeContractDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectRelativeContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.OptionalLong;

@Service
public class ProjectRelativeContractServiceImpl implements ProjectRelativeContractService {
    @Autowired
    private ProjectRelativeContractDao projectRelativeContractDao;

    @Override
    public Result queryByProjectId(Integer id) {
        return Result.ok(projectRelativeContractDao.queryByProjectId(id));
    }

    @Override
    public Result queryNotSubmitByUserId(Integer userId) {
        return Result.ok(projectRelativeContractDao.queryNotSubmit(userId));
    }

    @Override
    public Result addProjectRelativeContract(Integer userId, Map<String, Object> map) {
        projectRelativeContractDao.addProjectRelativeContract(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryRelativeLog(Map<String, Object> map) {
        projectRelativeContractDao.queryRelativeLog(map);
        return Result.ok();
    }

    @Override
    public Result delRelative(Map<String, Object> map) {
        projectRelativeContractDao.delRelative(map);
        return Result.ok();
    }

    @Override
    public Result submitRelative(Map<String, Object> map) {
        projectRelativeContractDao.submitRelative(map);
        return Result.ok();
    }
}
