package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ProjectDao;
import com.zpepdi.eureka_client.dao.appraise.ProjectWorkdayDeductDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectWorkdayDeductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProjectWorkdayDeductServiceImpl implements ProjectWorkdayDeductService {
    @Autowired
    private ProjectWorkdayDeductDao projectWorkdayDeductDao;
    @Autowired
    private ProjectDao projectDao;


    @Override
    public Result add(Integer id, Map<String, Object> map) {
        Map<String,Object> map1 = projectDao.queryUsedByTec(id,map);
        map1.put("manageUsable", sub(map1.get("manage"),map1.get("manageUsed")));
        map1.put("volumeUsable", sub(map1.get("volume"),map1.get("volumeUsed")));
        map1.put("backupUsable", sub(sub(
                sub(map1.get("amount"),map1.get("backupUsed"))
                ,map1.get("manage")),map1.get("volume")));
        if (compare(map1.get("manageUsable"), map.get("manage")) &&
                compare(map1.get("volumeUsable"), map.get("volume")) &&
                        compare(map1.get("backupUsable"), map.get("backup"))) {
            projectWorkdayDeductDao.add(id, map);
            return Result.ok();
        }else {
            return Result.build(377,"扣除工时超出异常");
        }
    }

    private double sub(Object a, Object b){
        return Double.parseDouble(a.toString()) - Double.parseDouble(b.toString());
    }

    private boolean compare(Object a, Object b){
        return  Double.parseDouble(a.toString()) >= Double.parseDouble(b.toString());
    }

    @Override
    public Result close(Integer id) {
        projectWorkdayDeductDao.close(id);
        return Result.ok();
    }

    @Override
    public Result queryLog(Integer id) {
        return Result.ok( projectWorkdayDeductDao.queryLog(id));
    }

    @Override
    public Result queryLogById(Integer id) {
        return Result.ok(projectWorkdayDeductDao.queryLogById(id));
    }

    @Override
    public Result queryLogByProject(Integer id) {
        return Result.ok(projectWorkdayDeductDao.queryLogByProject(id));
    }
}
