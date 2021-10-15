package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.Position;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.PositionFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = PositionFallbackService.class)
public interface PositionService {

  @RequestMapping("position/queryByWeight")
  Result queryByWeight(@RequestHeader("id") Integer id);

  @RequestMapping("position/query")
  Result query();


  @RequestMapping("position/update")
  Result update(@RequestBody List<Position> position);
}
