package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.GradeScore;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.GradeScoreFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = GradeScoreFallbackService.class)
public interface GradeScoreService {

    @RequestMapping("gradeScore/manage")
    Result manage(@RequestBody GradeScore gradeScore);


    @RequestMapping("gradeScore/queryTec")
    Result queryTec(@RequestHeader("id") Integer id);

    @RequestMapping("gradeScore/queryDep")
    Result queryDep(@RequestHeader("id") Integer id);

}
