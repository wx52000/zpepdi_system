package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.entity.PartParam;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.entity.UserScore;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.UserScoreService;
import com.zpepdi.feign_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("userScore")
public class UserScoreController {
    private UserScoreService userScoreService;
    @Autowired
    public void setUserScoreService(@Qualifier("com.zpepdi.feign_service.service.UserScoreService") UserScoreService userScoreService) {
        this.userScoreService = userScoreService;
    }

    @RequestMapping("queryByGradeId")
    public Result queryByGradeId(@RequestBody User user){
        return userScoreService.queryByGradeId(user);
    }

    @RequestMapping("queryByScoreId")
    public Result queryByScoreId(@RequestBody User user){
      return userScoreService.queryByScoreId(user);
    }

    @RequestMapping("selectByGradeId")
    public Result selectByGradeId(@RequestBody User user){
        return userScoreService.selectByGradeId(user);
    }

    @RequestMapping("appraise")
    public Result appraise(@UserId Integer userId, @RequestBody UserScore userScore){
        return userScoreService.appraise(userId,userScore);
    }

    @RequestMapping("queryScore")
    public Result queryScore(@RequestBody User user){
        return userScoreService.queryScore(user);
    }

    @RequestMapping("query")
    public Result query(@RequestBody User user){
        return userScoreService.query(user);
    }


    @RequestMapping("add")
    public Result add(@RequestBody List<UserScore> list){
        return userScoreService.add(list);
    }

    @RequestMapping("del")
    public Result del(@RequestBody UserScore userScore){
        return userScoreService.del(userScore);
    }

    //详情下载，本月
    @RequestMapping("detail")
    public Result detail(HttpServletResponse response,HttpServletRequest request){
        return Result.ok();
    }

    @RequestMapping("part")
    public Result part(@RequestBody PartParam partParam){
        return userScoreService.part(partParam);
    }
    @RequestMapping("partDownload")
    public Result partDownload(HttpServletResponse response ,HttpServletRequest request){
        return Result.ok();
    }

    //整体数据下载
//    @RequestMapping("personExcel")
//    public Result personExcel(HttpServletRequest request , HttpServletResponse response) {
//        return Result.ok();
//    }

}
