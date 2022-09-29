package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.NewsFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "EUREKA-CLIENT", fallback = NewsFallbackService.class)
public interface NewsService {

  @RequestMapping("news/count")
  Result count(@RequestHeader("id") Integer id);

  @RequestMapping("news/query")
  Result query(@RequestHeader("id") Integer id);
}
