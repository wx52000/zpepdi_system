package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ProjectTaskDao;
import com.zpepdi.eureka_client.service.ProjectWorkdayService;
import com.zpepdi.eureka_client.tools.DateUtils;
import com.zpepdi.eureka_client.tools.TaskNumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.appraise.ProjectDao;
import com.zpepdi.eureka_client.dao.appraise.ProjectWorkdayDao;
import com.zpepdi.eureka_client.dao.appraise.UserDao;
import com.zpepdi.eureka_client.entity.ProjectExcelTec;
import com.zpepdi.eureka_client.result.Result;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProjectWorkdayServiceImpl implements ProjectWorkdayService {
  private ProjectWorkdayDao proWorkdayDao;
  private ProjectDao projectDao;
  private UserDao userDao;
  @Autowired
  private ProjectTaskDao projectTaskDao;
  @Autowired
  public void setWorkdayDao(ProjectWorkdayDao projectWorkdayDao){
    this.proWorkdayDao = projectWorkdayDao;
  }
  @Autowired
  public void setProjectDao(ProjectDao projectDao){
    this.projectDao = projectDao;
  }
  @Autowired
  public void setUserDao(UserDao userDao){
    this.userDao = userDao;
  }
  @Override
  public Result drawLine(Integer id) {
    Map<String,Object> map = new HashMap();
    map.put("proWorkday",proWorkdayDao.queryProWorkday(id));
    map.put("tecWorkday",proWorkdayDao.queryTecWorkday(id));
    return Result.ok(map);
  }

  @Override
  public Result queryUsedTecWorkday(Integer id) {
    List<Map> list = proWorkdayDao.queryUsedTecWorkday(id);
    list.removeAll(Collections.singleton(null));
    return Result.ok(list);
  }

  @Override
  public Result queryProjectWorkday(Integer id) {
    return Result.ok(proWorkdayDao.queryProWorkday(id));
  }

  @Override
  public Result queryLimitAndAddSum(Integer id) {
    return Result.ok(proWorkdayDao.queryLimitAndAddSum(id));
  }

  @Override
  public Result queryProjectSumWorkday(Integer id) {
    return Result.ok(proWorkdayDao.queryProSumWorkday(id));
  }

  @Override
  public Result queryProjectTecWorkdayLog(Map<String, Object> map) {
    return Result.ok(proWorkdayDao.queryProjectTecWorkdayLog(map));
  }


  @Override
  public Result queryWorkdayByGeneral(Integer userId, Map<String,Object> map) {
    Map<String,Object> map1 = new HashMap<>();
    map1 = proWorkdayDao.queryWorkdayByGeneral(userId, Integer.valueOf(map.get("id").toString()));
    if(map.get("proNumber") != null) {
      AtomicReference<String> atomicReference = new AtomicReference<>(TaskNumberUtils.number(
              map.get("proNumber").toString(), "设总", map.get("type").toString()));
      map1.put("number", atomicReference.get());
    }
    return Result.ok(map1);
  }

  @Override
  public Result setWorkdayByGeneral(Integer userId, Map<String,Object> map) {
    Map<String,Object> map1 =
            proWorkdayDao.queryWorkdayByGeneral(userId, Integer.valueOf(map.get("pid").toString()));
    if (map1.get("check") != null && map1.get("check").equals(1)) {
      if (map.get("type").toString().equals("0")) {
        if (Double.parseDouble(map1.get("manage").toString()) - Double.parseDouble(map1.get("manageUsed").toString())
                >= Double.parseDouble(map.get("workday").toString())){
          map.put("date", DateUtils.getDateMonth(map.get("end").toString()));
          proWorkdayDao.setMWorkdayByGeneral(userId, map);
        }else {
          return Result.build(34,"工时不足");
        }
      } else {
        if (Double.parseDouble(map1.get("backup").toString()) - Double.parseDouble(map1.get("backupUsed").toString())
                >= Double.parseDouble(map.get("workday").toString())) {
          map.put("date", DateUtils.getDateMonth());
        }else {
            return Result.build(34,"工时不足");
          }
        proWorkdayDao.setBWorkdayByGeneral(userId, map);
      }
    }else {
      return Result.build(33,"设总工时未分配或设总工时违背审核");
    }
    return Result.ok();
  }


  @Override
  public Result setProWorkdayTotal(Integer id, Map map) {
    proWorkdayDao.setProWorkday(map);
    return Result.ok();
  }

  @Override
  public Result setPrincipal(Integer id, Map<String,Object> map){
    if (map.get("principal") != null && map.get("principal") != "") {
      proWorkdayDao.setTec(id, map);
      if (map.get("wtId") == null || map.get("wtId").toString().equals("")) {
        map.put("wtId", proWorkdayDao.queryWorkdayTecId(map));
      }
      proWorkdayDao.delPrincipal(id,map);
      if (map.get("principal") != null && !map.get("principal").toString().equals("[]")) {
        proWorkdayDao.setPrincipal(id, map);
      }
    }
    return Result.ok();
  }

  public Result delTec(Integer id,Map<String,Object> map){
    if (!proWorkdayDao.isOperate(id,map)) {
      proWorkdayDao.delTec(id, map);
    }else {
      return Result.build(677,"该专业主设人已对分配工时操作，不可删除");
    }
    return Result.ok();
  }

  @Override
  public Result setProWorkday(Integer id, Map map) {
    Map<String,Object> map1 = proWorkdayDao.queryProWorkday(Integer.valueOf(map.get("project_id").toString()));
      double manage = 0;
      double tec = 0;
      double backup = 0;
      if (map.get("manage") != ""){
        manage = Double.parseDouble(map.get("manage").toString());
      }else{
        manage = Double.parseDouble(map1.get("manage").toString());
      }
      if (map.get("tec") != ""){
        tec = Double.parseDouble(map.get("tec").toString());
      }else{
        tec = Double.parseDouble(map1.get("tec").toString());
      }
      if (map.get("backup") != ""){
        backup = Double.parseDouble(map.get("backup").toString());
      }else{
        backup = Double.parseDouble(map1.get("backup").toString());
      }
      if (map1.get("mold").toString().equals("1")){
        if (map.get("check").toString().equals("0")){
          map.put("check",1);
        }
      }
      if (map.get("manage") != "" || map.get("tec") != "" || map.get("backup") != "") {
      if (Double.parseDouble(map1.get("num").toString()) >= (manage + tec + backup)) {
        proWorkdayDao.setProWorkdayDistribut(id,map);
      } else {
        return Result.build(500, "管理工时和专业工时备用工时总数超出");
      }
    }
      if (map.get("tecWorkday") != null) {
        proWorkdayDao.setTecWorkday(id, Integer.valueOf(map.get("project_id").toString()),
                (List) Arrays.asList(map.get("tecWorkday")).get(0), Integer.valueOf(map.get("check").toString()));
      }
    return Result.ok();
  }

  @Override
  public Result setNewProWorkday(Integer id, Map map) {
    Integer projectId = Integer.valueOf(map.get("project_id").toString());
    Map<String,Object> limitMap = proWorkdayDao.queryLimitAndAddSum(projectId);
    if (limitMap == null){
      return Result.build(498,"该项目工时未分配");
    }
    if (Double.parseDouble(map.get("num").toString())
            > Double.parseDouble(limitMap.get("limit").toString())){
      return Result.build(499,"额外申请工时超出可申请上限");
    }
    proWorkdayDao.setNewProWorkday(id,map);
    proWorkdayDao.setNewProWorkdayDistribut(id,map);
    if (map.get("tecWorkday") != null) {
      proWorkdayDao.setNewTecWorkday(id,projectId,
              (List) Arrays.asList(map.get("tecWorkday")).get(0),
              Integer.valueOf(map.get("check").toString()), Integer.valueOf(map.get("addId").toString()));
    }
    return Result.ok();
  }

  @Override
  public Result queryNewWorkdayByAddId(Integer id) {
    return Result.ok(proWorkdayDao.queryNewWorkdayByAddId(id));
  }

  @Override
  public Result queryNewWorkday(Integer id) {
    return Result.ok(proWorkdayDao.queryNewWorkday(id));
  }


  @Override
  public Result delTecWorkday(Integer userId, Map map) {
    proWorkdayDao.delTecWorkday(map);
    return Result.ok();
  }

  @Override
  public List<ProjectExcelTec> statisticAll(String date) {
    return proWorkdayDao.statisticAll(date);
  }

  @Override
  public List<Map<String, Object>> userByPrincipal(String date,Integer userId, Integer id) {
    return userDao.workdayByPrincipal(date,userId,id);
  }


  @Override
  public List<Map<String,String>> statistic(Integer id) {
    List<Map<String,String>> list = proWorkdayDao.statistic(id);
    list.removeAll(Collections.singleton(null));
    return list;
  }

  @Override
  public List<Map<String, String>> everyoneAll(String date) {
    return proWorkdayDao.everyoneAll(date);
  }

  @Override
  public List<Map<String, String>> everyone(Map map) {
    return proWorkdayDao.everyone(map);
  }


  @Override
  public Result queryBackupList(Integer id) {
    return Result.ok(proWorkdayDao.queryBackupList(id));
  }
}
