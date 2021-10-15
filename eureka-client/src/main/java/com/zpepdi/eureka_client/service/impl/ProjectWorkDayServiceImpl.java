package com.zpepdi.eureka_client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.ProjectDao;
import com.zpepdi.eureka_client.dao.ProjectWorkDayDao;
import com.zpepdi.eureka_client.dao.UserDao;
import com.zpepdi.eureka_client.entity.ProjectExcelTec;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectWorkDayService;

import java.util.*;

@Service
public class ProjectWorkDayServiceImpl implements ProjectWorkDayService {
  private ProjectWorkDayDao proWorkDayDao;
  private ProjectDao projectDao;
  private UserDao userDao;
  @Autowired
  public void setWorkDayDao(ProjectWorkDayDao projectWorkDayDao){
    this.proWorkDayDao = projectWorkDayDao;
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
    map.put("proWorkDay",proWorkDayDao.queryProWorkDay(id));
    map.put("tecWorkDay",proWorkDayDao.queryTecWorkDay(id));
    return Result.ok(map);
  }

  @Override
  public Result queryUsedTecWorkDay(Integer id) {
    List<Map> list = proWorkDayDao.queryUsedTecWorkDay(id);
    list.removeAll(Collections.singleton(null));
    return Result.ok(list);
  }

  @Override
  public Result queryWorkDay(Integer id) {
    Map map = new HashMap();
    map.put("project",projectDao.queryById(id));
    map.put("workday",proWorkDayDao.queryProWorkDay(id));
    List<Map> list = proWorkDayDao.queryTecWorkDayAmount(id);
    list.removeAll(Collections.singleton(null));
    map.put("tecWorkday",list);
    return Result.ok(map);
  }

  @Override
  public Result queryProjectWorkDay(Integer id) {
    return Result.ok(proWorkDayDao.queryProWorkDay(id));
  }

  @Override
  public Result queryMajorWorkDay(Integer id) {
    Map<String,Object> map = new HashMap<>();
    map.put("major",proWorkDayDao.queryProWorkDay(id).get("tec"));
    List<Map> list = proWorkDayDao.queryTecWorkDayAmount(id);
    list.removeAll(Collections.singleton(null));
    map.put("workday",  list);
    return Result.ok(map);
  }

  @Override
  public Result queryBackupWorkDay(Map<String, String> map) {
    Map<String,Object> map1 = new HashMap<>();
    map1.put("backup",proWorkDayDao.queryProWorkDay(Integer.valueOf(map.get("id"))).get("backup"));
    map1.put("usable",proWorkDayDao.queryUsableBackupWorkday(Integer.valueOf(map.get("id"))));
    List<Map> list =   proWorkDayDao.queryBackupWorkDayAmount(map);
    list.removeAll(Collections.singleton(null));
    map1.put("workday", list);
    return Result.ok(map1);
  }

  @Override
  public Result queryTecVolumeRatio(Integer id) {
    return Result.ok(proWorkDayDao.queryTecVolumeRatio(id));
  }

  @Override
  public Result setTecVolumeRatio(List<Map<String,Object>> list) {
    proWorkDayDao.setTecVolumeRatio(list);
    return Result.ok();
  }

  @Override
  public Result queryReserveWorkday(Integer id) {
    List<Map> list = proWorkDayDao.queryReserveWorkDayRatio(id);
    list.removeAll(Collections.singleton(null));
    return Result.ok(list);
  }

  @Override
  public Result setProWorkDay(Map map) {
    proWorkDayDao.setProWorkDay(map);
    return Result.ok();
  }

  @Override
  public Result setTecWorkDay(Map map) {
    proWorkDayDao.setTecWorkDay(Integer.valueOf(map.get("project_id").toString()),
      (List) Arrays.asList(map.get("list")).get(0));
    return Result.ok();
  }

  @Override
  public Result setBackupWorkDay(Map map) {
    List l =  (List) Collections.singletonList(map.get("list")).get(0);
    proWorkDayDao.setBackupWorkDay(Integer.valueOf(map.get("project_id").toString()),
      (List) Collections.singletonList(map.get("list")).get(0), map.get("date").toString());
    return Result.ok();
  }

  @Override
  public Result setUserWorkDay(Map map) {
    List list = (List) Arrays.asList(map.get("list")).get(0);
    proWorkDayDao.setUserWorkDay(Integer.valueOf(map.get("project_id").toString()),
      (List) Arrays.asList(map.get("list")).get(0),Integer.valueOf(map.get("type").toString()));
    return Result.ok();
  }

  @Override
  public List<ProjectExcelTec> statisticAll(String min,String max) {
    return proWorkDayDao.statisticAll(min,max);
  }

  @Override
  public List<Map<String,String>> statistic(Integer id) {
    List<Map<String,String>> list = proWorkDayDao.statistic(id);
    list.removeAll(Collections.singleton(null));
    return list;
  }

  @Override
  public List<Map<String, String>> everyoneAll(Map map) {
    return proWorkDayDao.everyoneAll(map);
  }

  @Override
  public List<Map<String, String>> everyone(Map map) {
    return proWorkDayDao.everyone(map);
  }

  @Override
  public List<Map<String, String>> personal(Map<String,String> map) {
    User user = userDao.queryById(Integer.valueOf(map.get("id")));
    map.put("name",user.getName());
    return proWorkDayDao.personal(map);
  }
}
