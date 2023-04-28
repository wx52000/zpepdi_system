package com.zpepdi.eureka_client.service.impl;

import com.alibaba.fastjson.JSON;
import com.zpepdi.eureka_client.dao.appraise.CheckerDao;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.feign.AuditInformationFeign;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.CheckerService;
import com.zpepdi.eureka_client.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CheckerServiceImpl implements CheckerService {
    @Autowired
    private CheckerDao checkerDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AuditInformationFeign auditInformationFeign;

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
        return Result.ok();
    }

    @Override
    public Result setDepChecker(Integer userId, Map<String, Object> map) {
        checkerDao.setDepChecker(map);
        return Result.ok();
    }

    @Override
    public Result setCheckerAmount(Integer userId, Map<String, Object> map) {
        Map<String,Object> checker = checkerDao.queryChecker2();
        map.put("checkerId", checker.get("id"));
        checkerDao.setCheckerAmount(userId,map);
        checkerDao.setCheckerUsed(map);
        map.put("auditType", 14);
        map.put("information",map.get("department") + String.valueOf(DateUtils.getYearOfDate(new Date()))
                + "年度无产值部门工时申请");
        map.put("auditKey", map.get("key"));
        map.put("data", JSON.toJSONString(map));
        map.put("auditor_id", map.get("checkerId"));
        map.put("auditor_username",checker.get("username"));
        map.put("auditor_name", checker.get("name"));
        List<Object> auditList = new ArrayList<>();
        auditList.add(map.get("key"));
        map.put("auditList",auditList);
        auditInformationFeign.addAuditInformation(map);
        return Result.ok();
    }


    @Override
    public Result queryByUser(Integer id) {
        return Result.ok(checkerDao.queryByUserId(id));
    }
}
