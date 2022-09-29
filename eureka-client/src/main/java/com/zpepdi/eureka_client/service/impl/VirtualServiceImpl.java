package com.zpepdi.eureka_client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zpepdi.eureka_client.dao.appraise.UserDao;
import com.zpepdi.eureka_client.dao.appraise.VirtualDao;
import com.zpepdi.eureka_client.entity.*;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.VirtualService;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class VirtualServiceImpl implements VirtualService {
  private VirtualDao virtualDao;
  private UserDao userDao;
  @Autowired
  public void setVirtualDao(VirtualDao virtualDao){
    this.virtualDao = virtualDao;
  }
  @Autowired
  public void setUserDao(UserDao userDao){
    this.userDao = userDao;
  }
  @Override
  public Result setProject(Virtual virtual) {
    virtualDao.setProject(virtual);
    //人员添加
    if (virtual.getGeneral().size()>0) {
      virtual.getGeneral().forEach(item -> {
          item.put("role", 1);
      });
      virtualDao.addUser(virtual.getId(), virtual.getGeneral());

    }
    if (virtual.getPrincipal().size() >0) {
//      virtual.getPrincipal().stream()..forEach(item -> {
//        if (item.get("workday") != null && item.get("workday") != ""){
//          sum += Double.parseDouble(item.get("workday").toString());
//        }
//        item.put("role", 2);
//      });
      double sum = 0;
      sum = virtual.getPrincipal().stream().peek(item -> item.put("role", 2)).
              filter(item ->item.get("workday") != null && item.get("workday") != "")
              .mapToDouble(item -> Double.parseDouble(item.get("workday").toString())).sum();
      if (virtual.getWorkday() >= sum) {
        virtualDao.addUser(virtual.getId(), virtual.getPrincipal());
        virtualDao.addUserWorkday(virtual.getId(), virtual.getPrincipal(), virtual.getMonth());
      }else {
        return Result.build(500,"工时总数超出上限");
      }
    }
    virtualDao.setState(virtual.getId(),virtual.getGeneral(),1);
    virtualDao.setState(virtual.getId(),virtual.getPrincipal(),2);
    return Result.ok();
  }

  @Override
  public Result query() {
    return Result.ok(virtualDao.query());
  }

  @Override
  public Result queryByUser(Integer id) {
    return Result.ok(virtualDao.queryByUser(id));
  }

  @Override
  public Result queryById(Map<String,Object> map) {
    Map<String,Object> map1 = new HashMap();
    map1 = virtualDao.queryById(Integer.valueOf(map.get("id").toString()));
    map1.put("general", virtualDao.queryRole(map,1));
    map1.put("principal", virtualDao.queryRole(map,2));
    return Result.ok(map1);
  }

  @Override
  public Result queryUsedWorkday(Map<String, Object> map,Integer userId) {
    return Result.ok(virtualDao.queryUsedWorkday( map,userId));
  }

  @Override
  public Result queryWorkday(Map<String, Object> map, Integer id) {
    Map<String,Object> map1 = new HashMap<>();
    map1.put("list",virtualDao.queryWorkday(map,id));
    map1.put("used",virtualDao.queryUsedWorkday(map,id));
    return Result.ok(map1);
  }


  @Override
  public Result setWorkday(Map<String,Object> map, Integer id) {
    virtualDao.setWorkday(map, id);
    return Result.ok();
  }



}
