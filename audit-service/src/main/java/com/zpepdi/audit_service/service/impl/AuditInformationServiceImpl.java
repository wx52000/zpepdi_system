package com.zpepdi.audit_service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zpepdi.audit_service.dao.*;
import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;
import com.zpepdi.audit_service.service.AuditInformationService;
import com.zpepdi.audit_service.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class AuditInformationServiceImpl implements AuditInformationService {

    @Autowired
    private AuditInformationDao auditInformationDao;
    @Autowired
    private AuditDataDao auditDataDao;
    @Autowired
    private AuditorInformationDao auditorInformationDao;
    @Autowired
    private AdditionalDataDao additionalDataDao;
    @Autowired
    private AdditionalDBStateDao additionalDBStateDao;
    @Autowired
    private AdditionalConditionDao additionalConditionDao;


    @Override
    public Result queryCount(User user) {
        return Result.ok(auditInformationDao.queryCount(user));
    }

    @Override
    @Transactional
    public Result addAuditInformation(User user, Map<String, Object> map) {
        boolean operation =  true;
        if (map.get("auditKey") != null && !map.get("auditKey").toString().equals("")){
            Map<String,Object> data = auditInformationDao.queryByKey(user,map);
            if (data != null){
                if (data.get("operation").toString().equals("1")){
                    auditInformationDao.delAuditInformation(Integer.valueOf(data.get("id").toString()));
                }else if (data.get("operation").toString().equals("2")){
                    operation = false;
                }
            }
        }
        if (operation) {
            auditInformationDao.addAuditInformation(user, map);
            auditorInformationDao.addAuditor(map);
            if (map.get("auditList") != null && !map.get("auditList").toString().equals("[]")) {
                auditDataDao.addAuditData(map);
            }
        }
        return Result.ok();
    }

    @Override
    @Transactional
    public Result renew(User user, Map<String, Object> map) {
            auditInformationDao.renew(map);
            auditorInformationDao.renew(map);
            if (map.get("auditList") != null && !map.get("auditList").toString().equals("[]")) {
                auditDataDao.addAuditData(map);
            }
        return Result.ok();
    }

    @Override
    public Result queryBySelf(User user, Map<String, Object> map) {
        return Result.ok(auditInformationDao.queryBySelf(user, map));
    }

    @Override
    @Transactional
    public Result submitAdult(User user, Map<String, Object> map) {
        auditorInformationDao.updateAuditor(user,map);
        int type = Integer.parseInt(map.get("type").toString());
        switch (type){
            case 0 :
                additionalDBStateDao.auditOperation0(user,map);
                break;
            case 1 :
                additionalDBStateDao.auditOperation1(user,map);
                break;
            case 3 :
                additionalDBStateDao.auditOperation3(user,map);
                break;
            case 4 :
                additionalDBStateDao.auditOperation4(user,map);
                auditInformationDao.updateData(Integer.valueOf(map.get("id").toString()),JSON.toJSONString(map.get("data")));
                break;
            case 5 :
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map.get("data")));
                if (!DateUtils.getDateMonth(
                                new Date().getTime() - (3600L * 24 *(int) additionalConditionDao.confirmDay() * 1000))
                        .equals(jsonObject.get("declareDate").toString())) {
                    map.put("declareDate", DateUtils.getDateMonth());
                }else {
                    map.put("declareDate",jsonObject.get("declareDate"));
                }
                additionalDBStateDao.auditOperation5(user,map);
                break;
            case 6 :
                additionalDBStateDao.auditOperation6(user,map);
                auditInformationDao.updateData(Integer.valueOf(map.get("id").toString()),JSON.toJSONString(map.get("data")));
                break;
            case 7 :
                JSONObject jsonObject7 = JSON.parseObject(JSON.toJSONString(map.get("data")));
                if (map.get("workday") == null || map.get("workday").toString().equals("")){
                    return Result.build(587,"项目工时未赋值");
                }
                map.put("auditKey",jsonObject7.get("auditKey"));
                if (jsonObject7.get("type").toString().equals("0")){
                    if (jsonObject7.get("abbreviate").toString().equals("FD")){
                        additionalDBStateDao.auditOperation7(user, map);
                    }else {
                        if (jsonObject7.get("tec").toString().equals("综合")){
                            additionalDBStateDao.auditOperation7(user, map);
                        }else {
                            additionalDBStateDao.auditOperation7Type0Other(user, map);
                        }
                    }
                }else if (jsonObject7.get("type").toString().equals("1")) {
                    if (jsonObject7.get("abbreviate").toString().equals("FD")){
                        Map<String,Object> depWorkday = additionalConditionDao.queryDepWorkday(user,jsonObject7);
                        double amount = 0, used = 0;
                        if (depWorkday != null){
                            amount = Double.parseDouble(depWorkday.get("amount").toString());
                            used = Double.parseDouble(depWorkday.get("used").toString());
                        }
                        if (amount - used < Double.parseDouble(map.get("workday").toString())){
                            return Result.build(588,"可用无产值部门工时不足");
                        }else {
                            additionalDBStateDao.auditOperation7Type1(user, map);
                        }
                    }else {
                        additionalDBStateDao.auditOperation7(user, map);
                    }
                }
                break;
            case 9 :
                additionalDBStateDao.auditOperation9(user,map);
                break;
            case 10 :
                additionalDBStateDao.auditOperation10(user,map);
                auditInformationDao.updateData(Integer.valueOf(map.get("id").toString()),JSON.toJSONString(map.get("data")));
                break;
            case 11:
                int declareDay = additionalConditionDao.declareDay();
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                if (day > declareDay){
                    map.put("workday_month",DateUtils.dateToString(calendar.getTime(),"yyyy-MM"));
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.add(Calendar.MONTH,1);
                    String plan_month = DateUtils.dateToString(calendar1.getTime(),"yyyy-MM");
                    map.put("plan_month", plan_month);
                    String start = plan_month + "-01";
                    calendar1.add(Calendar.MONTH,1);
                    calendar1.set(Calendar.DAY_OF_MONTH,0);
                    String end = DateUtils.dateToString(calendar1.getTime(),"yyyy-MM-dd");
                    map.put("start",start);
                    map.put("end",end);
                }else {
                    JSONObject jsonObject1 = JSONObject.parseObject(JSON.toJSONString(map.get("data")));
                    Calendar calendar1 = Calendar.getInstance();
                    String plan_month = DateUtils.dateToString(calendar1.getTime(),"yyyy-MM");
                    map.put("plan_month", jsonObject1.get("planMonth"));
                    map.put("workday_month",jsonObject1.get("workdayMonth"));
                    String start = plan_month + "-01";
                    map.put("start",start);
                    calendar1.add(Calendar.MONTH,1);
                    calendar1.set(Calendar.DAY_OF_MONTH,0);
                    String end = DateUtils.dateToString(calendar1.getTime(),"yyyy-MM-dd");
                    map.put("end",end);
                }
                additionalDBStateDao.auditOperation11(user,map);
                break;
            case 12 :
                additionalDBStateDao.auditOperation12(user,map);
                auditInformationDao.updateData(Integer.valueOf(map.get("id").toString()),JSON.toJSONString(map.get("data")));
                break;
            case 13 :
                additionalDBStateDao.auditOperation13(user,map);
                auditInformationDao.updateData(Integer.valueOf(map.get("id").toString()),JSON.toJSONString(map.get("data")));
                break;
            default:
                break;
        }
        auditInformationDao.updateState(map);
        return Result.ok();
    }

    @Override
    public Result queryById(Integer id) {
        Map<String,Object> data = auditInformationDao.queryById(id);
        JSONObject jsonObject = JSON.parseObject(data.get("data").toString());
        Integer type = Integer.valueOf(data.get("type").toString());
        Integer state = Integer.valueOf(data.get("state").toString());
//        此处采用查询时更新数据的方式，以防止数据过期,数据库中数据在审核时重新更新
        if (state == 0) {
            switch (type) {
                case 1 :
                    Integer projectId = Integer.valueOf(jsonObject.get("project_id").toString());
                    Map<String, Object> map = additionalDataDao.queryType1(projectId);
                    jsonObject.put("additionalSum", map.get("num"));
                    break;
                case 4:
                    jsonObject.put("list", additionalDataDao.queryType4(jsonObject));
                    break;
                case 6:
                    jsonObject.put("list", additionalDataDao.queryType6(jsonObject));
                    break;
                case 9:
                    jsonObject.put("list", additionalDataDao.queryType9(jsonObject));
                    break;
                case 10:
                    jsonObject.put("handler", data.get("initiator_id"));
                    jsonObject.put("list", additionalDataDao.queryType10(jsonObject));
                    break;
                case 12:
                    jsonObject.put("handler", data.get("initiator_id"));
                    jsonObject.put("list", additionalDataDao.queryType12(jsonObject));
                    break;
                case 13:
                    jsonObject.put("handler", data.get("initiator_id"));
                    jsonObject.put("list", additionalDataDao.queryType13(jsonObject));
                    break;
                }
            }
        return Result.ok(jsonObject);
    }

    @Override
    public Result delAuditInformation(Integer id) {
        auditInformationDao.delAuditInformation(id);
        return Result.ok();
    }
}
