package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.fallback.ActivityFallbackService;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "EUREKA-CLIENT")
public interface Down360Service {

    @RequestMapping("down/down360")
    Response down360();
}
