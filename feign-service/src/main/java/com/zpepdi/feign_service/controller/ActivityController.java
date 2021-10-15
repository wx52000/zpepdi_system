package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.entity.Activity;
import com.zpepdi.feign_service.entity.PrincipalWorkday;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.entity.VirtualDesigner;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("activity")
public class ActivityController {
  private ActivityService activityService;
  @Autowired
  public void setActivityService(@Qualifier("com.zpepdi.feign_service.service.ActivityService") ActivityService activityService){
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

  @RequestMapping("queryById")
  public Result queryById(@RequestHeader Integer id){
    return activityService.queryById(id);
  }

  @RequestMapping("queryByUser")
  public Result queryByUser(@UserId Integer userId){
    return activityService.queryByUser(userId);
  }

  @RequestMapping("queryWorkday")
  public Result queryWorkday(@RequestBody Map<String,Object> map, @UserId Integer id){
    return activityService.queryWorkday(map,id);
  }

  @RequestMapping("setWorkday")
  public Result setWorkday(@RequestBody Map<String,Object> map, @UserId Integer userId){
    return activityService.setWorkday(map,userId);
  }
}
