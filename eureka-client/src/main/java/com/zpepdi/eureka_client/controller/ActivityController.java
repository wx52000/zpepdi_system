package com.zpepdi.eureka_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.Activity;
import com.zpepdi.eureka_client.entity.PrincipalWorkday;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.VirtualDesigner;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ActivityService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("activity")
public class ActivityController {
  private ActivityService activityService;
  @Autowired
  public void setActivityService(ActivityService activityService){
    this.activityService = activityService;
  }

  @RequestMapping("setActivity")
  public Result setProject(@RequestBody Activity activity){
    return activityService.setProject(activity);
  }

  @RequestMapping("query")
  public Result query(){
    return activityService.query();
  }

  @RequestMapping("queryByUser")
  public Result queryByUser(@RequestHeader Integer userId){
    return activityService.queryByUser(userId);
  }

  @RequestMapping("queryById")
  public Result queryById(@RequestHeader Integer id){
    return activityService.queryById(id);
  }

  @RequestMapping("queryWorkday")
  public Result queryWorkday(@RequestBody Map<String,Object> map, @RequestHeader("userId") Integer id){
    return activityService.queryWorkday(map,id);
  }

  @RequestMapping("setWorkday")
  public Result setWorkday(@RequestBody Map<String,Object> map, @RequestHeader Integer userId){
    return activityService.setWorkday(map,userId);
  }
}
