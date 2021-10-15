package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.TecRatio;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.TecRatioFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "EUREKA-CLIENT", fallback = TecRatioFallbackService.class)
public interface TecRatioService {

    @RequestMapping("tecRation/queryByTec")
    Result queryByTec(@RequestHeader("id") Integer id);
}
