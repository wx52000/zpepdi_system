package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.ReasonFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = ReasonFallbackService.class)
public interface ReasonService {

    @RequestMapping("reason/queryByType")
    Result queryByType(@RequestHeader("type") Integer type);
}
