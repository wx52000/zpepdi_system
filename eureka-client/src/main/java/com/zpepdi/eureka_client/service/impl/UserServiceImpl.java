package com.zpepdi.eureka_client.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpepdi.eureka_client.dao.appraise.RangeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.appraise.DepartmentDao;
import com.zpepdi.eureka_client.dao.appraise.TechnologyDao;
import com.zpepdi.eureka_client.dao.appraise.UserDao;
import com.zpepdi.eureka_client.entity.Department;
import com.zpepdi.eureka_client.entity.ExcelData;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.UserOut;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.UserService;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private DepartmentDao departmentDao;
    private TechnologyDao technologyDao;
    private RangeDao rangeDao;
    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao){
      this.departmentDao = departmentDao;
    }
    @Autowired
    public void setTechnologyDao(TechnologyDao technologyDao){
      this.technologyDao = technologyDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void setRangeDao(RangeDao rangeDao){
        this.rangeDao = rangeDao;
    }


    @Override
    public Result queryById(Integer id) {
        return Result.ok(userDao.queryById(id));
    }

    @Override
    public Result queryByUserIdSameDep(Integer id, String name) {
        return Result.ok(userDao.queryByUserIdSameDep(id,name));
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void addExcel(List<ExcelData> list) {
        userDao.addExcel(list);
    }

    @Override
    public void del(Integer id) {
        userDao.del(id);
    }

    @Override
    public void upd(Map<String,Object> user) {
        String[]  strings = {"tid","pid","did"};
        for (String s : strings) {
            if (!(user.get(s) instanceof Integer)){
                user.put(s,null);
            }
        }
        userDao.upd(user);
    }

    @Override
    public void pwdReset(Integer id) {
        userDao.pwdReset(id);
    }

    @Override
    public Result information(Integer id,Map<String,Object> map) {
        return Result.ok(userDao.information(id,map));
    }

    @Override
    public Result workdayLogById(Integer id, Map<String,Object> map) {
//        User user = userDao.queryById(id);
        return Result.ok(userDao.workdayLogById(id,map));
    }

    @Override
    public Result queryByProject(Integer id) {
        return Result.ok(userDao.queryByProject(id));
    }

    @Override
    public Result workdayLog(Map<String, Object> map) {
        return Result.ok(userDao.workdayAllLogById(map));
    }

    @Override
    public List<Map> query(User user) {
        return userDao.query(user);
    }

    @Override
    public Result workday(Map<String,Object> map) {
        return Result.ok(userDao.workday(map));
    }

    @Override
    public Result projectRole() {
        return Result.ok(userDao.projectRole());
    }

    @Override
    public Result manageProjectRole(Integer id) {
        return Result.ok(userDao.manageProjectRole(id));
    }

    @Override
    public Result projectGeneral(Integer id) {
        return Result.ok(userDao.projectGeneral(id));
    }

    @Override
    public Result projectPrincipal(Integer id) {
        return Result.ok(userDao.projectPrincipal(id));
    }

    @Override
    public Result volumeDesigner(Integer id) {
        return Result.ok(userDao.volumeDesigner(id));
    }

    @Override
    public Result volumeChecker(Integer id) {
        return Result.ok(userDao.volumeChecker(id));
    }

    @Override
    public Result volumeHeadman(Integer id) {
        return Result.ok(userDao.volumeHeadman(id));
    }

    @Override
    public Result workdayByManager(Integer id, Map<String,Object> map) {
        return Result.ok(userDao.workdayByManager(id, map));
    }

    @Override
    public Result workdayByGeneral(Integer userId, Map<String,Object> map) {
        return Result.ok(userDao.workdayByGeneral(userId,map));
    }

    @Override
    public Result logByGeneral(Integer id, Map<String,Object> map) {
//        User user = userDao.queryById(Integer.valueOf(map.get("userId").toString()));
//        map.put("name",user.getName());
        return Result.ok(userDao.logByGeneral(id,map));
    }

    @Override
    public Result workdayByPrincipal(String date, Integer userId, Integer id) {
        return Result.ok(userDao.workdayByPrincipal(date,userId,id));
    }

    @Override
    public Result logByPrincipal(Integer id, Map<String,Object> map) {
        map.put("userId",id);
        return Result.ok(userDao.logByPrincipal(id,map));
    }

    @Override
    public Result workdayByTec(Integer userId, Map<String,Object> map) {
        return Result.ok(userDao.workdayByTec(map,userId));
    }

    @Override
    public Result logByTec(Integer id, Map<String,Object> map) {
        return Result.ok(userDao.logByTec(id,map));
    }

    @Override
    public Result workdayByProject(String date, Integer id) {
        return Result.ok(userDao.workdayByProject(date,id));
    }

    @Override
  public List<String> queryGrade() {
    return userDao.queryGrade();
  }

  @Override
  public List<Map> queryAll() {
    return userDao.queryAll();
  }

  @Override
  public Map queryToupd(Integer id) {
      Map map = userDao.queryToupd(id);
      map.put("position",userDao.queryPosition(id));
    return map;
  }

  @Override
    public List<Map> queryByTid(Integer id) {
        return userDao.queryByTid(id);
    }

    //未使用
  @Override
  public PageInfo<Map> queryNotSelf(User user) {
//      User user1 = userDao.queryById(user.getId());
//      if (user1.getGrade() == 1) {
//        PageHelper.startPage(user.getPageIndex(), user.getPageSize(), true);
//        PageInfo<Map> pageInfo = new PageInfo<>(userDao.queryNotSelf(user));
//        return pageInfo;
//      }else
        return null;
  }

  @Override
  public Result queryToScore(Integer id, Map<String,Object> map) {
    Map map1 = userDao.queryLimits(id);
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    if (day <= 15) {
        calendar.add(Calendar.MONTH, -4);
    }else {
        calendar.add(Calendar.MONTH, -3);
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    String date = sdf.format(calendar.getTime());
    date = date + "-" + "1";
    map.put("id",id);
    map.put("date", date);
    int pid = Integer.parseInt(map1.get("pid").toString());
    if (map1.get("grade").equals(1)) {
        if (pid == 1 || pid == 2 || pid == 3) {
            return Result.ok(userDao.queryNotSelf(map));
        }
      //经理
      else if (pid == 4) {
          return Result.ok(userDao.queryByManager(map));
      }
      else
        return Result.ok(userDao.queryByGAndP(map));
    }else
      return Result.build(1001,"暂无打分权限");
  }

  @Override
  public Result queryScoreList(User user) {
    Map map = userDao.queryLimits(user.getId());
    if (Integer.parseInt(map.get("grade").toString()) == 1) {
        if (Integer.parseInt(map.get("limits").toString()) == 0 || Integer.parseInt(map.get("limits").toString()) == 1) {
            return Result.ok(userDao.queryNotSelfList(user));
      }
      //经理
      else if (Integer.parseInt(map.get("limits").toString()) == 2) {
        return Result.ok(userDao.queryByManagerList(user));
      }
      else
        return Result.ok(userDao.queryByGAndPList(user));
    }else
      return Result.ok(null);
  }

  @Override
    public PageInfo<Map> queryNotScore(User user) {
        PageHelper.startPage(user.getPageIndex(),user.getPageSize(),true);
        PageInfo<Map> pageInfo = new PageInfo<>(userDao.queryNotScore(user));
        return pageInfo;
    }

  @Override
  public Result queryAppriseAll() {
      User user = new User();
    Calendar calendar = Calendar.getInstance();
    Integer year = calendar.get(Calendar.YEAR);
    Integer month;
    if (calendar.get(Calendar.MONTH)%3 == 2) {
      if (calendar.get(Calendar.DAY_OF_MONTH) >= 25) {
        month = calendar.get(Calendar.MONTH)-2;
      }else
        month = calendar.get(Calendar.MONTH)-3;
    }else
      month = calendar.get(Calendar.MONTH)-3-calendar.get(Calendar.MONTH)%3;
    if (month <=0)
      year--;
    user.setSqlDate(year + "-" + month + "-1");
    return  Result.ok(userDao.queryAppriseAll(user));
  }

  @Override
  public List<UserOut> queryAppraise(User user) {
    return userDao.queryAppraise(user);
  }

  @Override
  public List<UserOut> queryNotAppraise(User user) {
    Calendar calendar = Calendar.getInstance();
    Integer year = calendar.get(Calendar.YEAR);
    Integer month;
    if (calendar.get(Calendar.MONTH)%3 == 2) {
      if (calendar.get(Calendar.DAY_OF_MONTH) >= 25) {
        month = calendar.get(Calendar.MONTH)-2;
      }else
      month = calendar.get(Calendar.MONTH)-3;
    }else
      month = calendar.get(Calendar.MONTH)-3-calendar.get(Calendar.MONTH)%3;
    if (month <=0)
      year--;
    user.setSqlDate(year + "-" + month + "-1");
    return userDao.queryNotAppraise(user);
  }

  @Override
  public List<UserOut> queryNotScored(User user) {
    return userDao.queryNotScored(user);
  }

  @Override
  public List<UserOut> queryNotTecApp(User user) {
    return userDao.queryNotTecApp(user);
  }

  @Override
    public List<Map> queryByTec(Integer id) {
        return userDao.queryByTec(id);
    }

    @Override
    public List<Map> queryByName(User user) {
        return userDao.queryByName(user);
    }

    @Override
    public List<Integer> queryByUsername(List<ExcelData> list) {
        return userDao.queryByUsername(list);
    }

    @Override
    public void paw(Integer id,User user) {
        userDao.paw(id,user);
    }

    @Override
    public List<Map> queryPrincipal(Integer id) {
        return userDao.queryPrincipal(id);
    }

  @Override

  public List<Map> userAll(Integer mode) {
      List<Map> list = new ArrayList<>();
      List<Department> dep = departmentDao.queryNotUser();
      for (int i = 0 ; i < dep.size(); i++){
        Map map = new HashMap();
        map.put("id",dep.get(i).getId());
        map.put("pid",0);
        map.put("label",dep.get(i).getName());
        List<Map> tecList = new ArrayList<>();
        List<Map> tec = technologyDao.queryBydepNoU(dep.get(i).getId());
        for (int j = 0; j < tec.size(); j++){
          Map map1 = new HashMap();
          map1.put("id",dep.get(i).getId() + "-" + tec.get(j).get("id"));
          map1.put("pid" , dep.get(i).getId());
          map1.put("label", tec.get(j).get("name"));
          map1.put("children", userDao.queryByT((Integer) tec.get(j).get("id"),mode));
          tecList.add(map1);
        }
        map.put("children" , tecList);
        list.add(map);
      }
    return list;
  }

  @Override
  public List<Map> userAllAndState(Integer id) {
    List<Map> list = new ArrayList<>();
    List<Department> dep = departmentDao.queryNotUser();
    for (int i = 0 ; i < dep.size(); i++){
      Map map = new HashMap();
      map.put("id",dep.get(i).getId());
      map.put("pid",0);
      map.put("label",dep.get(i).getName());
      List<Map> tecList = new ArrayList<>();
      List<Map> tec = technologyDao.queryBydepNoU(dep.get(i).getId());
      for (int j = 0; j < tec.size(); j++){
        Map map1 = new HashMap();
        map1.put("id",dep.get(i).getId() + "-" + tec.get(j).get("id"));
        map1.put("pid" , dep.get(i).getId());
        map1.put("label", tec.get(j).get("name"));
        map1.put("children", userDao.queryByTAndState(id,(Integer) tec.get(j).get("id")));
        tecList.add(map1);
      }
      map.put("children" , tecList);
      list.add(map);
    }
    return list;
  }

  @Override
  public List<Map> userAllAndGroup(Integer id,Integer mode) {
    List<Map> list = new ArrayList<>();
    List<Department> dep = departmentDao.queryNotUser();
    for (int i = 0 ; i < dep.size(); i++){
      Map map = new HashMap();
      map.put("id", "d-" + dep.get(i).getId());
      map.put("label",dep.get(i).getName());
      List<Map> tecList = new ArrayList<>();
      List<Map> tec = technologyDao.queryBydepNoU(dep.get(i).getId());
      for (int j = 0; j < tec.size(); j++){
        Map map1 = new HashMap();
        map1.put("id", "t-" + dep.get(i).getId() + "-" + tec.get(j).get("id"));
        map1.put("label", tec.get(j).get("name"));
        if (mode == 0) {
          //业务
          map1.put("children", userDao.queryByTAndGroup((Integer) tec.get(j).get("id"), id));
        }else if (mode == 1){
          //活动
          map1.put("children", userDao.queryByTAndGroup1((Integer) tec.get(j).get("id"), id));
        }
        tecList.add(map1);
      }
      map.put("children" , tecList);
      list.add(map);
    }
    return list;
  }

}
