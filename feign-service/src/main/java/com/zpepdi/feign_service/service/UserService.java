package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.entity.UserOut;
import com.zpepdi.feign_service.fallback.UserFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zpepdi.feign_service.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@FeignClient(value = "EUREKA-CLIENT", fallback = UserFallbackService.class)
public interface UserService {

    @RequestMapping("user/add")
    Result add(@RequestBody User user);

    @RequestMapping("user/del")
    Result del(@RequestHeader("id") Integer id);

    @RequestMapping("user/upd")
    Result upd(@RequestBody Map<String,Object> user);

    @RequestMapping("user/pwdReset")
    Result pwdReset(@RequestHeader("id") Integer id);

    @RequestMapping("user/query")
    Result query(@RequestBody User user);

    @RequestMapping("user/queryById")
    Result queryById(@RequestHeader("userId") Integer id);

    @RequestMapping("user/information")
    Result information(@RequestHeader("userId") Integer userId);

    @RequestMapping("user/workdayLogById")
    Result workdayLogById(@RequestHeader("userId") Integer userId);

    @RequestMapping("user/workdayLog")
    Result workdayLog(@RequestBody Map<String,Object> map);

    @RequestMapping("user/workday")
    Result workday(@RequestHeader("queryDate") String queryDate);

    @RequestMapping("user/queryToupd")
    Result queryToupd(@RequestHeader("id") Integer id);

    @RequestMapping("user/queryByTid")
    Result queryByTid(@RequestHeader("id") Integer id);

    @RequestMapping("user/queryNotSelf")
    Result queryNotSelf(@RequestBody User user);

    @RequestMapping("user/queryToScore")
    Result queryToScore(@RequestHeader("userId") Integer userId,@RequestBody Map<String,Object> map);

    @RequestMapping("user/queryScoreList")
    Result queryScoreList(@RequestBody User user);

    @RequestMapping("user/queryNotScore")
    Result queryNotScore(@RequestBody User user);

    @RequestMapping("user/queryAppriseAll")
    Result queryAppriseAll();

    @RequestMapping("user/queryAppraise")
    Result queryAppraise(@RequestBody User user);

    @RequestMapping("user/queryNotAppraise")
    Result queryNotAppraise(@RequestBody User user);

    @RequestMapping("user/queryNotScored")
    Result queryNotScored(@RequestBody User user);

    @RequestMapping("user/queryNotTecApp")
    Result queryNotTecApp(@RequestBody User user);

    @RequestMapping("user/queryByTec")
    Result queryByTec(@RequestHeader("id") Integer id);

    @RequestMapping("user/queryByName")
    Result queryByName(@RequestBody User user);

    @RequestMapping("user/paw")
    Result paw(@RequestHeader("userId") Integer userId,@RequestBody User user);

    //普通树
    @RequestMapping("user/userAll")
    Result userAll(@RequestHeader("mode") Integer mode);

    //评价管理树，包含是否可评价状态
    @RequestMapping("user/userAllAndState")
    Result userAllAndState(@RequestHeader("id") Integer id);

    //主设人选择树，包含主设人分组
    @RequestMapping("user/userAllAndGroup")
    Result userAllAndGroup(@RequestHeader("id") Integer id,@RequestHeader("mode") Integer mode);

//    @RequestMapping("user/excel")
//    Result personExcel(HttpServletRequest request , HttpServletResponse response);
}
