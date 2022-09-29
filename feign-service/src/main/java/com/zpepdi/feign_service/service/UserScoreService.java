package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.*;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.UserScoreFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@FeignClient(value = "EUREKA-CLIENT", fallback = UserScoreFallbackService.class)
public interface UserScoreService {

    @RequestMapping("userScore/queryByGradeId")
    Result queryByGradeId(@RequestBody User user);

    @RequestMapping("userScore/queryByScoreId")
    Result queryByScoreId(@RequestBody User user);

    @RequestMapping("userScore/selectByGradeId")
    Result selectByGradeId(@RequestBody User user);

    @RequestMapping("userScore/appraise")
    Result appraise(@RequestHeader("userId")Integer id, @RequestBody UserScore userScore);

    @RequestMapping("userScore/queryScore")
    Result queryScore(@RequestBody User user);

    @RequestMapping("userScore/query")
    Result query(@RequestBody User user);


    @RequestMapping("userScore/add")
    Result add(@RequestBody List<UserScore> list);

    @RequestMapping("userScore/del")
    Result del(@RequestBody UserScore userScore);

    //详情下载，本月
//    @RequestMapping("userScore/detail")
//    Result detail(HttpServletResponse response, HttpServletRequest request);

    @RequestMapping("userScore/part")
    Result part(@RequestBody PartParam partParam);

//    @RequestMapping("userScore/partDownload")
//    Result partDownload(HttpServletResponse response ,HttpServletRequest request);

    //整体数据下载
//    @RequestMapping("userScore/personExcel")
//    Result personExcel(HttpServletRequest request , HttpServletResponse response);

}
