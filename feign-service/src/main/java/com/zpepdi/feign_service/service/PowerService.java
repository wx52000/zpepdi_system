package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.Power;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.PowerFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "EUREKA-CLIENT", fallback = PowerFallbackService.class)
public interface PowerService {

    @RequestMapping("power/query")
    Result query();
}
