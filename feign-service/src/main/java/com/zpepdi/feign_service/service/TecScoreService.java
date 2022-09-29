package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.*;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.TecScoreFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@FeignClient(value = "EUREKA-CLIENT", fallback = TecScoreFallbackService.class)
public interface TecScoreService {

    @RequestMapping("tecScore/queryByGradeId")
    Result queryByGradeId(@RequestHeader("id") Integer id);

    @RequestMapping("tecScore/queryByScoreId")
    Result queryByScoreId(@RequestBody User user);

    @RequestMapping("tecScore/appraise")
    Result appraise(@RequestHeader("userId") Integer userId, @RequestBody TecScore tecScore);

    @RequestMapping("tecScore/queryScore")
    Result queryScore(@RequestBody User user);

    @RequestMapping("tecScore/query")
    Result query(@RequestBody User user);

//    @RequestMapping("tecScore/detail")
//    Result detail(HttpServletResponse response,HttpServletRequest request);

    @RequestMapping("tecScore/part")
    Result part(@RequestBody PartParam partParam);


//    @RequestMapping("tecScore/excel")
//    Result excel(HttpServletRequest request , HttpServletResponse response) throws IOException;

}
