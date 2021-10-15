package com.zpepdi.feign_service.service;


import com.zpepdi.feign_service.fallback.ProjectTecFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "EUREKA-CLIENT", fallback = ProjectTecFallbackService.class)
public interface ProjectTecService {

    @RequestMapping("projectTec/query")
    Result query(@RequestHeader("id") Integer id);
}
