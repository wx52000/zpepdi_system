package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.Range;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.RangeFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = RangeFallbackService.class)
public interface RangeService {

    @RequestMapping("range/query")
    Result query();

    @RequestMapping("range/update")
     Result update(@RequestBody Range range);

    @RequestMapping("range/queryDate")
    Result queryDate();

    @RequestMapping("range/updateDate")
     Result updateDate(@RequestBody Range range);
}
