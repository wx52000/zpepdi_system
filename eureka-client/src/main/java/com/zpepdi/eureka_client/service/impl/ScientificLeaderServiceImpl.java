package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ScientificLeaderDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScientificLeaderService;
import com.zpepdi.eureka_client.tools.Download;
import com.zpepdi.eureka_client.tools.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class ScientificLeaderServiceImpl implements ScientificLeaderService {
    @Autowired
    private ScientificLeaderDao scientificLeaderDao;

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
        return Result.ok(map);
    }

    @Override
    public Result setToCheck(Integer id) {
        scientificLeaderDao.setToCheck(id);
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
