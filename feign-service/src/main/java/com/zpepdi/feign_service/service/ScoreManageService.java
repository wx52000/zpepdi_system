package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.ScoreManage;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.ScoreManageFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "EUREKA-CLIENT", fallback = ScoreManageFallbackService.class)
public interface ScoreManageService {

  @RequestMapping("scoreManage/handle")
  Result handle(@RequestBody ScoreManage scoreManage);
}
