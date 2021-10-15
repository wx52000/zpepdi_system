package com.zpepdi.eureka_client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.ActivityDao;
import com.zpepdi.eureka_client.dao.UserDao;
import com.zpepdi.eureka_client.entity.Activity;
import com.zpepdi.eureka_client.entity.PrincipalWorkday;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.VirtualDesigner;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ActivityService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class ActivityServiceImpl implements ActivityService {
  private ActivityDao activityDao;
  private UserDao userDao;
  @Autowired
  public void setActivityDao(ActivityDao activityDao){
    this.activityDao = activityDao;
  }
  @Autowired
  public void setUserService(UserDao userDao){
    this.userDao = userDao;
  }
  @Override
  public Result setProject(Activity activity) {
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//    Pattern pattern = Pattern.compile("[^0-9]");
    activity.setStart_date(activity.getDate().get(0));
    activity.setEnd_date(activity.getDate().get(1));
    activityDao.setProject(activity);
    if (activity.getGeneral().size()>0) {
      activity.getGeneral().forEach(item -> {
        item.put("role", 1);
      });
      activityDao.addUser(activity.getId(), activity.getGeneral());
    }
    if (activity.getPrincipal().size() >0) {
      activity.getPrincipal().forEach(item -> {
        item.put("role", 2);
      });
      activityDao.addUser(activity.getId(), activity.getPrincipal());
    }
    activityDao.setState(activity.getId(),activity.getGeneral(),1);
    activityDao.setState(activity.getId(),activity.getPrincipal(),2);
    return Result.ok();
  }

  @Override
  public Result query() {
    List<Map> list = activityDao.query();
    return Result.ok(list);
  }

  @Override
  public Result queryByUser(Integer id) {
    return Result.ok(activityDao.queryByUser(id));
  }

  @Override
  public Result queryById(Integer id) {
    Map<String,Object> map = new HashMap();
    map = activityDao.queryById(id);
    List<String> list = new ArrayList<>();
    list.add(map.get("start_date").toString());
    list.add(map.get("end_date").toString());
    map.put("date", list);
    map.put("general",activityDao.queryByRole(id,1));
    map.put("principal",activityDao.queryByRole(id,2));
    return Result.ok(map);
  }

  @Override
  public Result queryWorkday(Map<String, Object> map, Integer userId) {
    Map<String,Object> map1 = new HashMap<>();
    map1.put("list",activityDao.queryWorkday(map,userId));
    map1.put("used",activityDao.queryUsedWorkday(map,userId));
    return Result.ok(map1);
  }


  @Override
  public Result setWorkday(Map<String,Object> map,Integer userId) {
    activityDao.setWorkday(map,userId);
    return Result.ok();
  }


}
