package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.Department;
import com.zpepdi.feign_service.fallback.ActivityFallbackService;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "EUREKA-CLIENT", fallback = DepartmentFallbackService.class)
public interface DepartmentService {

    @RequestMapping("department/query")
    Result query();

    @RequestMapping("department/queryNotUser")
    Result queryNotUser();

    @RequestMapping("department/add")
    Result add(@RequestBody Department department);

    @RequestMapping("department/del")
    Result del(@RequestHeader("id") Integer id);
}
