package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.Technology;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.TechnologyFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = TechnologyFallbackService.class)
public interface TechnologyService {

    @RequestMapping("technology/query")
    Result query(@RequestHeader("id") Integer id);

    @RequestMapping("technology/queryNotUser")
    Result queryNotUser();

    @RequestMapping("technology/evaluate")
    Result evaluate(@RequestHeader("userId") Integer userId);

    @RequestMapping("technology/add")
    Result add(@RequestBody Technology technology);

    @RequestMapping("technology/del")
    Result del(@RequestHeader("id") Integer id);

    @RequestMapping("technology/queryToSelected")
    Result queryToSelected();

    @RequestMapping("technology/queryById")
    Result queryById(@RequestBody Project project);
}
