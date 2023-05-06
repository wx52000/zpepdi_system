package com.zpepdi.eureka_client.service.impl;

import com.alibaba.fastjson.JSON;
import com.zpepdi.eureka_client.dao.appraise.ProjectThawDao;
import com.zpepdi.eureka_client.dao.appraise.UserDao;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.feign.AuditInformationFeign;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectThawService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectThawServiceImpl implements ProjectThawService {
    @Autowired
    private ProjectThawDao projectThawDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuditInformationFeign auditInformationFeign;
    @Override
    public Result addProjectThaw(Integer userId, Map<String, Object> map) {
        projectThawDao.addProjectThaw(userId,map);
        User user = userDao.queryById(Integer.valueOf(map.get("checkerId").toString()));
        map.put("auditType", 15);
        map.put("information",map.get("name") + "项目解冻申请");
        map.put("auditKey", map.get("id"));
        map.put("data", JSON.toJSONString(map));
        map.put("auditor_id", user.getId());
        map.put("auditor_username", user.getUsername());
        map.put("auditor_name", user.getName());
        List<Object> auditList = new ArrayList<>();
        auditList.add(map.get("id"));
        map.put("auditList",auditList);
        auditInformationFeign.addAuditInformation(map);
        return Result.ok();
    }
}
