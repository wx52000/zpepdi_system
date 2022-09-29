package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.Activity;
import com.zpepdi.feign_service.entity.PrincipalWorkday;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.entity.VirtualDesigner;
import com.zpepdi.feign_service.fallback.ActivityFallbackService;
import com.zpepdi.feign_service.fallback.UserFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = ActivityFallbackService.class)
public interface ActivityService {

  @RequestMapping("activity/setActivity")
  Result setProject(@RequestBody Activity activity);

  @RequestMapping("activity/query")
  Result query();

  @RequestMapping("activity/queryByUser")
  Result queryByUser(@RequestHeader("userId") Integer userId);

  @RequestMapping("activity/queryById")
  Result queryById(@RequestHeader("id") Integer id);

  @RequestMapping("activity/queryWorkday")
  Result queryWorkday(@RequestBody Map<String,Object> map, @RequestHeader("userId") Integer id);

  @RequestMapping("activity/setWorkday")
  Result setWorkday(@RequestBody Map<String,Object> map, @RequestHeader("userId") Integer userId);
}
