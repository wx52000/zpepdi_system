package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ProjectDao;
import com.zpepdi.eureka_client.dao.appraise.ProjectTaskDao;
import com.zpepdi.eureka_client.dao.appraise.VolumeDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectTaskService;
import com.zpepdi.eureka_client.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

    @Autowired
    private ProjectTaskDao projectTaskDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private VolumeDao volumeDao;


    @Override
    public Result addTask(Integer id,Map<String, Object> map) {
        map.put("date", DateUtils.getDateMonth());
        Map<String,Object> map1 = new HashMap<>();
        if (map.get("workday") != null && map.get("workday") != "") {
            map1.put("id", map.get("pid"));
            map1.put("tec", map.get("tec"));
            map1 = projectDao.queryUsedByTec(id, map1);
            String usedField = "";
            String numField = "";
            if (map.get("type").equals(0)){
                usedField = "manageUsed";
                numField = "manage";
            }else{
                usedField = "backupUsed";
                numField = "backup";
                map1.put("backup",Double.parseDouble(map1.get("amount").toString()) -
                        Double.parseDouble(map1.get("volume").toString()) - Double.parseDouble(map1.get("manage").toString()));
            }
            BigDecimal used = new BigDecimal(map1.get(usedField).toString()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal num = new BigDecimal(map1.get(numField).toString()).setScale(2,RoundingMode.HALF_UP);
            BigDecimal workday = new BigDecimal(map.get("designer_workday").toString())
                    .add(new BigDecimal(map.get("principal_workday").toString()))
                    .add(new BigDecimal(map.get("checker_workday").toString()))
                    .add(new BigDecimal(map.get("headman_workday").toString()));
            if (num.subtract(used).compareTo(workday) > -1)  {
                projectTaskDao.addTask(id,map);
            }else {
                return Result.build(560,"工时超出上限，请在任务列表重新输入");
        }
        }
        return Result.ok(map);
    }

    @Override
    public Result update(Integer id,Map<String, Object> map) {
        projectTaskDao.update(map);
        return Result.ok();
    }

    @Override
    public Result taskWorkday(Integer id, Map<String, Object> map) {
        projectTaskDao.taskWorkday(map);
        return Result.ok();
    }

    @Override
    public Result queryByHumanId(Integer id) {
        projectTaskDao.queryByHumanId(id);
        return Result.ok();
    }

    @Override
    public Result setAdvance(Map<String, Object> map) {
        projectTaskDao.setAdvance(map);
        return Result.ok();
    }

    @Override
    public Result del(Map<String,Object> map) {
        if (!map.get("spider").toString().equals("1")) {
            if (map.get("type").toString().equals("设总备用")) {
                if (map.get("check").toString().equals("1")) {
                    return Result.build(600, "设总已审核，不能删除");
                }
            } else if (map.get("type").toString().equals("设总管理")) {
                if (!DateUtils.getDateMonth().equals(map.get("complete_time").toString())) {
                    return Result.build(600, "工时统计月份已过，不能删除");
                }
            } else {
                if (map.get("end") != null) {
                    if (DateUtils.getDateMonth().equals(DateUtils.getDateMonth(map.get("end").toString()))) {
                        return Result.build(600, "，不能删除");
                    }
                }
            }
            projectTaskDao.del(map);
        }else {
            volumeDao.del(Integer.valueOf(map.get("id").toString()));
        }
        return Result.ok();
    }
}
