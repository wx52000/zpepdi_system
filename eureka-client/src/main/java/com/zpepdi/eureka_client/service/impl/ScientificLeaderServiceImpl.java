package com.zpepdi.eureka_client.service.impl;

import com.alibaba.fastjson.JSON;
import com.zpepdi.eureka_client.dao.appraise.ScientificDao;
import com.zpepdi.eureka_client.dao.appraise.ScientificLeaderDao;
import com.zpepdi.eureka_client.feign.AuditInformationFeign;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScientificLeaderService;
import com.zpepdi.eureka_client.tools.Download;
import com.zpepdi.eureka_client.tools.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScientificLeaderServiceImpl implements ScientificLeaderService {
    @Autowired
    private ScientificLeaderDao scientificLeaderDao;
    @Autowired
    private ScientificDao scientificDao;
    @Autowired
    private AuditInformationFeign auditInformationFeign;

    @Override
    public Result setLeader(Integer userId, Map<String, Object> map) {
        scientificLeaderDao.setLeader(userId,map);
        return Result.ok(map);
    }

    @Override
    public Result setLeaderChecker(Integer userId,Map<String, Object> map) {
        scientificLeaderDao.setLeaderChecker(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryLeader(Integer id) {
        return Result.ok(scientificLeaderDao.queryLeader(id));
    }

    @Override
    public Result querySurplus(Integer id) {
        return Result.ok(scientificLeaderDao.querySurplus(id));
    }

    @Override
    public Result delLeader(Integer id) {
        scientificLeaderDao.delLeader(id);
        return Result.ok();
    }

    @Override
    public Result addTermByGeneral(Integer userId, Map<String, Object> map) {
        if (Double.parseDouble(map.get("workday").toString()) > scientificLeaderDao
                .querySurplus(Integer.valueOf(map.get("projectId").toString()))){
            return Result.build(660,"工时超出");
        }
        scientificLeaderDao.addTermByGeneral(userId,map);
        return Result.ok();
    }

    @Override
    public Result addTermByLeader(Integer userId, Map<String, Object> map) {
        scientificLeaderDao.addTermByLeader(userId,map);
        Map<String,Object> project = scientificDao.queryById(userId, Integer.valueOf(map.get("projectId").toString()));
        map.put("auditType",9);
        map.put("information",(project.get("number") != null ? project.get("number")+"-" : "") + project.get("name") + "科技工时申请");
        map.put("auditKey",map.get("id"));
        map.put("list",scientificLeaderDao.queryFilesByTerm(Integer.valueOf(map.get("id").toString())));
        map.put("data", JSON.toJSONString(map));
        map.put("auditor_id", project.get("generalId"));
        map.put("auditor_username", project.get("generalUsername"));
        map.put("auditor_name", project.get("general"));
        List<Object> auditList = new ArrayList<>();
        auditList.add(map.get("id"));
        map.put("auditList",auditList);
        auditInformationFeign.addAuditInformation(map);
        return Result.ok(map);
    }

    @Override
    public Result setToCheck(Integer userId, Integer id) {
        scientificLeaderDao.setToCheck(id);
        Map<String,Object> project = scientificLeaderDao.queryTermById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("auditType",9);
        map.put("information",(project.get("pNumber") != null ? project.get("pNumber")+"-" : "") + project.get("pName") + "科技工时申请");
        map.put("auditKey",id);
        map.put("list",scientificLeaderDao.queryFilesByTerm(id));
        map.put("data", JSON.toJSONString(map));
        map.put("auditor_id", project.get("checkerId"));
        map.put("auditor_username", project.get("checkerNumber"));
        map.put("auditor_name", project.get("checkerName"));
        List<Object> auditList = new ArrayList<>();
        auditList.add(id);
        map.put("auditList",auditList);
        auditInformationFeign.addAuditInformation(map);
        return Result.ok();
    }

    @Override
    public Result uploadFile(Integer userId, MultipartFile file, Map<String, Object> map) {
        map = UploadUtils.upload(file,map);
        scientificLeaderDao.addScientificTermFile(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryTerm(Integer userId, Integer projectId) {
        return Result.ok(scientificLeaderDao.queryTerm(userId,projectId));
    }

    @Override
    public Result queryFilesByTerm(Integer id) {
        return Result.ok(scientificLeaderDao.queryFilesByTerm(id));
    }

    @Override
    public HttpServletResponse downFiles(Integer id, HttpServletResponse response) {
        Map<String,Object> map = scientificLeaderDao.queryFilesById(id);
        Download.downloadFile(response,map.get("upload_location").toString(), map.get("name").toString());
        return response;
    }
}
