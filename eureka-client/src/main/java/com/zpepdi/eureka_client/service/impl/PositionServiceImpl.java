package com.zpepdi.eureka_client.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.appraise.PositionDao;
import com.zpepdi.eureka_client.entity.Position;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.PositionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImpl implements PositionService {
  private PositionDao positionDao;
  @Autowired
  private RedisTemplate<String,Object> redisTemplate;
  @Autowired
  private void setPositionDao(PositionDao positionDao){
    this.positionDao = positionDao;
  }

  @Override
  public Result query() {
    return Result.ok(positionDao.query());
  }

  @Override
  public Result newRole(String name) {
    positionDao.newRole(name);
    return Result.ok();
  }

  @Override
  public Result delRole(Integer id) {
    if (id == 1) {
      return Result.build(220,"超级管理员不允许删除或修改");
    }
    positionDao.delRole(id);
    return Result.ok();
  }

  @Override
  public List<Map> queryByWeight(Integer id) {
    return positionDao.queryByWeight(id);
  }

  @Override
  public Result update(List<Position> position) {
    try {
      positionDao.update(position);
      return  Result.ok();
    }catch (Exception e){
      e.printStackTrace();
      return  Result.build(1000,"修改失败");
    }
  }

  @Override
  public Result queryRoleMenus() {
    return Result.ok(positionDao.queryRoleMenus());
  }

  @Override
  public Result queryByRoleId(Integer id) {
    String redisEle =  "role"+id;
    Object object = redisTemplate.opsForValue().get(redisEle);
    if (object == null){
      Map<String,Object> map= new HashMap<>();
      map.put("menus",positionDao.queryTreeByRoleId(id));
      map.put("button",positionDao.queryButtonByRoleId(id));
      object = map;
      redisTemplate.opsForValue().set(redisEle, JSONObject.toJSONString(map));
    }else {
      object = JSONObject.parseObject(object.toString());
    }
    return Result.ok(object);
  }

  @Override
  public Result queryRole(Integer id) {
      Map<String,Object> map= new HashMap<>();
      map.put("menus",positionDao.queryRole(id));
      map.put("button",positionDao.queryRoleButton(id));
    return Result.ok(map);
  }

  // type == 0 为菜单链接 用于动态菜单 type == 1其余为动态按钮
  @Override
  public Result setRoleMenus(Map<String,Object> map) {
    if (map.get("roleId").toString().equals("1")) {
      return Result.build(220,"超级管理员不允许删除或修改");
    }
    String redisEle =  "role"+ map.get("roleId").toString();
    redisTemplate.delete(redisEle);
    if (map.get("type").toString().equals("0")) {
      if (map.get("state").toString().equals("true")) {
        positionDao.setMenus(map);
      } else {
        positionDao.delMenus(map);
      }
    } else {
      if (map.get("state").toString().equals("true")) {
        positionDao.setButton(map);
      } else {
        positionDao.delButton(map);
      }
    }
    return Result.ok();
  }
}
