package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.UserPositionFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = UserPositionFallbackService.class)
public interface UserPositionService {

  @RequestMapping("userPosition/add")
  Result add(@RequestBody Map map);

  @RequestMapping("userPosition/del")
  Result del(@RequestHeader("id") Integer id);
}
