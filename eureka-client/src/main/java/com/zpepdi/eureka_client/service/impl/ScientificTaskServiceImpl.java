package com.zpepdi.eureka_client.service.impl;

import com.alibaba.fastjson.JSON;
import com.zpepdi.eureka_client.dao.appraise.ScientificDao;
import com.zpepdi.eureka_client.dao.appraise.ScientificTaskDao;
import com.zpepdi.eureka_client.feign.AuditInformationFeign;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScientificTaskService;
import com.zpepdi.eureka_client.tools.TaskNumberUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ScientificTaskServiceImpl implements ScientificTaskService {
    @Autowired
    private ScientificTaskDao scientificTaskDao;
    @Autowired
    private ScientificDao scientificDao;
    @Autowired
    private AuditInformationFeign auditInformationFeign;

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
    public Result taskSubmit(Integer userId, Map<String, Object> map) {
        scientificTaskDao.taskSubmit(map);
        Map<String,Object> project = scientificTaskDao.queryProjectByTask(Integer.valueOf(map.get("id").toString()));
        map.put("auditType",10);
        map.put("information",project.get("name") + "科技工时发放");
        map.put("projectId",project.get("id"));
        map.put("handler", userId);
        map.put("submit_date",map.get("submitDate"));
        map.put("auditKey",project.get("id").toString() +userId + map.get("submitDate"));
        map.put("data", JSON.toJSONString(map));
        map.put("auditor_id", project.get("checkerId"));
        map.put("auditor_username", project.get("checkerNumber"));
        map.put("auditor_name", project.get("checkerName"));
        auditInformationFeign.addAuditInformation(map);
        return Result.ok();
    }
}
