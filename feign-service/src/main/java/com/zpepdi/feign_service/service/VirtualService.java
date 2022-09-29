package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.PrincipalWorkday;
import com.zpepdi.feign_service.entity.Virtual;
import com.zpepdi.feign_service.entity.VirtualDesigner;
import com.zpepdi.feign_service.fallback.VirtualFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = VirtualFallbackService.class)
public interface VirtualService {

  @RequestMapping("virtual/setProject")
  Result setProject(@RequestBody Virtual virtual);

  @RequestMapping("virtual/query")
  Result query();

  @RequestMapping("virtual/queryByUser")
  Result queryByUser(@RequestHeader("userId") Integer id);

  @RequestMapping("virtual/queryById")
  Result queryById(@RequestBody Map<String,Object> map);

  @RequestMapping("virtual/queryUsedWorkday")
  Result queryUsedWorkday(@RequestBody Map<String,Object> map,@RequestHeader("userId") Integer userId);

  @RequestMapping("virtual/queryWorkday")
  Result queryWorkday(@RequestBody Map<String,Object> map, @RequestHeader("userId") Integer id);

  @RequestMapping("virtual/setWorkday")
  Result setWorkday(@RequestBody Map<String,Object> map,@RequestHeader("userId") Integer userId);

}
