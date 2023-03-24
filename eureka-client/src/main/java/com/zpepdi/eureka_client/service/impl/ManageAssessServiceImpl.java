package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ManageAssessDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ManageAssessService;
import com.zpepdi.eureka_client.service.ProjectService;
import com.zpepdi.eureka_client.tools.DateUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class ManageAssessServiceImpl implements ManageAssessService {

    @Autowired
    private ManageAssessDao manageAssessDao;
    @Autowired
    private ProjectService projectService;

    @Override
    public Result queryDidAssessSum() {
        manageAssessDao.setManageAssessUser();
        return Result.ok();
    }

    @Override
    public Result setUserAssess(Integer userId, Map<String, Object> map) {
        String  date = getDeclareMonth();
        map.put("submit_date",date);
        if (map.get("workday") != null && !map.get("workday").equals("")){
            double have= 0, used = 0,workday = Double.parseDouble(map.get("workday").toString());
            Map<String,Object> assess = manageAssessDao.queryAssessSum(userId,date);
            if (map.get("type").toString().equals("0")){
                have = Double.parseDouble(assess.get("formal").toString());
                used = Double.parseDouble(assess.get("formalUsed").toString());
            }else {
                have = Double.parseDouble(assess.get("external").toString());
                used = Double.parseDouble(assess.get("externalUsed").toString());
            }
            if (BigDecimal.valueOf(have).compareTo(BigDecimal.valueOf(workday+used)) > -1) {
                manageAssessDao.setUserAssess(userId, map);
            }else {
                return Result.build(9912,"可用工时不足");
            }
        }
        return Result.ok();
    }

    @Override
    public Result setAssessRemark(Integer userId, Map<String, Object> map) {
        manageAssessDao.setUserAssess(userId, map);
        return Result.ok();
    }

    @Override
    public Result queryAssessSum(Integer userId) {
        return Result.ok(manageAssessDao.queryAssessSum(userId,getDeclareMonth()));
    }
    private String getDeclareMonth(){
        int declareDay = projectService.declareDay();
        Calendar calendar = Calendar.getInstance();
        String date = DateUtils.getDateMonth(calendar.getTimeInMillis());
        if (calendar.get(Calendar.DAY_OF_MONTH) <= declareDay){
            calendar.add(Calendar.MONTH,-1);
            date = DateUtils.getDateMonth(calendar.getTimeInMillis());
        }
        return date;
    }
}