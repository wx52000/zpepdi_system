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
    Map<String,Object> map1 = proWorkDayDao.queryProWorkDay(id);
    if (map1 != null) {
      map.put("major", map1.get("tec"));
    }
    List<Map> list = proWorkDayDao.queryTecWorkDayAmount(id);
    list.removeAll(Collections.singleton(null));
    map.put("workday",  list);
    return Result.ok(map);
  }

  @Override
  public Result queryBackupWorkDay(Map<String, String> map) {
    Map<String,Object> map1 = new HashMap<>();
    Map<String,Object> map2 = proWorkDayDao.queryProWorkDay(Integer.valueOf(map.get("id")));
    if (map2 != null) {
      map1.put("backup", map2.get("backup"));
    }
    map1.put("musable", proWorkDayDao.queryUsableManageWorkday(Integer.valueOf(map.get("id"))));
    map1.put("manage", proWorkDayDao.queryUsedManageWorkdayByDate(Integer.valueOf(map.get("id")),map.get("date")));
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
    Map<String,Object> map1 = proWorkDayDao.queryProWorkDay(Integer.valueOf(map.get("project_id").toString()));
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
      if (map.get("manage") != "" || map.get("tec") != "" || map.get("backup") != "") {
      if (Double.parseDouble(map1.get("num").toString()) >= (manage + tec + backup)) {
        proWorkDayDao.setProWorkDayDistribut(map);
      } else {
        return Result.build(500, "管理工时和专业工时备用工时总数超出");
      }
    }
    return Result.ok();
  }

  @Override
  public Result setTecWorkDay(Map map) {
    proWorkDayDao.setTecWorkDay(Integer.valueOf(map.get("project_id").toString()),
      (List) Arrays.asList(map.get("list")).get(0));
    return Result.ok();
  }

  @Override
  public Result setManageWorkday(Integer id ,Map map) {
    double manage = Double.parseDouble(
            proWorkDayDao.queryProWorkDay(Integer.valueOf(map.get("project_id").toString())).get("manage").toString());
    if (manage >= Double.parseDouble(map.get("workday").toString())) {
      proWorkDayDao.setManageWorkday(id, map);
    }else{
      return Result.build(500, "管理工时分配总数超出");
    }
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
  public List<ProjectExcelTec> statisticAll(String date) {
    return proWorkDayDao.statisticAll(date);
  }

  @Override
  public List<Map<String,String>> statistic(Integer id) {
    List<Map<String,String>> list = proWorkDayDao.statistic(id);
    list.removeAll(Collections.singleton(null));
    return list;
  }

  @Override
  public List<Map<String, String>> everyoneAll(String date) {
    return proWorkDayDao.everyoneAll(date);
  }

  @Override
  public List<Map<String, String>> everyone(Map map) {
    return proWorkDayDao.everyone(map);
  }

  @Override
  public List<Map<String, Object>> personal(Integer id) {
    Calendar calendar = Calendar.getInstance();
    String date = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1);
    User user = userDao.queryById(id);
    return userDao.workdayLogById(id,date,user.getName());
  }
}
