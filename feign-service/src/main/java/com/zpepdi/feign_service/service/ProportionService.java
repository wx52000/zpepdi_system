package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.Proportion;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.ProportionFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = ProportionFallbackService.class)
public interface ProportionService {

    @RequestMapping("proportion/add")
    Result add(@RequestBody Proportion proportion);

    @RequestMapping("proportion/queryLastTime")
    Result queryLastTime(@RequestBody Proportion proportion);

    @RequestMapping("proportion/queryLastTwoTimes")
    Result queryLastTwoTime(@RequestBody Proportion proportion);
}
