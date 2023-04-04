package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ProjectRelativeContractDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectRelativeContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProjectRelativeContractServiceImpl implements ProjectRelativeContractService {
    @Autowired
    private ProjectRelativeContractDao projectRelativeContractDao;
    @Override
    public Result addProjectRelativeContract(Integer userId, Map<String, Object> map) {
        projectRelativeContractDao.addProjectRelativeContract(userId,map);
        return Result.ok();
    }
}
