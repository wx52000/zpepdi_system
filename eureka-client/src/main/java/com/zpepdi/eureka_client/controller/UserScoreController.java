package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.PartParam;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.UserScore;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.UserScoreService;
import com.zpepdi.eureka_client.service.UserService;
import com.zpepdi.eureka_client.tools.Download;
import com.zpepdi.eureka_client.excel.ExcelProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("userScore")
public class UserScoreController {
    private UserService userService;
    private UserScoreService userScoreService;
    @Autowired
    public void setUserScoreService(UserScoreService userScoreService){
        this.userScoreService = userScoreService;
    }
    @Autowired
    public void setUserService(UserService userService){
      this.userService = userService;
    }

    @RequestMapping("queryByGradeId")
    public Result queryByGradeId(@RequestBody User user){
        return Result.ok(userScoreService.queryByGradeId(user));
    }

    @RequestMapping("queryByScoreId")
    public Result queryByScoreId(@RequestBody User user){
      return Result.ok(userScoreService.queryByScoreId(user));
    }

    @RequestMapping("selectByGradeId")
    public Result selectByGradeId(@RequestBody User user){
        return Result.ok(userScoreService.selectByGradeId(user));
    }
//评价
    @RequestMapping("appraise")
    public Result appraise(@UserId Integer userId, @RequestBody UserScore userScore){
        userScoreService.appraise(userId,userScore);
        return Result.ok();
    }

    @RequestMapping("queryScore")
    public Result queryScore( @RequestBody User user){
        return Result.ok(userScoreService.queryScore(user));
    }

    @RequestMapping("query")
    public Result query(@RequestBody User user){
        return Result.ok(userScoreService.query(user));
    }


    @RequestMapping("add")
    public Result add(@RequestBody List<UserScore> list){
        userScoreService.add(list);
        return Result.ok();
    }

    @RequestMapping("del")
    public Result del(@RequestBody UserScore userScore){
        userScoreService.del(userScore);
        return Result.ok();
    }

    //详情下载，本月
    @RequestMapping("detail")
    public Result detail(HttpServletResponse response,HttpServletRequest request){
      String s= "";
      try {
        User user = new User();
        user.setThisMonth(Integer.valueOf(request.getParameter("month")));
        ExcelProperty excelProperty = new ExcelProperty();
        List<String> userName = userService.queryGrade();
        Future<String> future = excelProperty
          .personalDetailsExcel(userName,userScoreService.detail(user,userName));
        s = future.get();
        String fileName = user.getThisMonth()+ "月个人评分详情表.xlsx";
        Download.downloadFile( response , "D://excel/" + "excel.xlsx" , fileName);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
      return  Result.ok(s);
    }

  @RequestMapping("part")
  public Result part(@RequestBody PartParam partParam){
    String s= "";
    String fileName = "";
    try {
      Calendar calendar = Calendar.getInstance();
      fileName = calendar.getTimeInMillis() + "个人评分详情表.xlsx";
      ExcelProperty excelProperty = new ExcelProperty();
      Future<String> future = excelProperty
        .personalPartExcel(userScoreService.part(partParam.getMode(),partParam.getList(),partParam.getMonth()),fileName);
      s = future.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return  Result.ok(fileName);
  }
  @RequestMapping("partDownload")
  public Result partDownload(HttpServletResponse response ,HttpServletRequest request){
    Download.downloadFile( response , "D://excel/" + request.getParameter("fileName") ,request.getParameter("fileName"));
    return Result.ok();
  }

    //整体数据下载
    @RequestMapping("personExcel")
    public Result personExcel(HttpServletRequest request , HttpServletResponse response) {
        String s = "";
        try {
            User user = new User();
            user.setId(Integer.valueOf(request.getParameter("id")));
            user.setThisMonth(Integer.valueOf(request.getParameter("month")));
            ExcelProperty excelProperty = new ExcelProperty();
            Future<String> future = excelProperty
              .personalExcel(userScoreService.excel1(user));
            s = future.get();
            String fileName = user.getThisMonth() + "月个人得分汇总表.xlsx";
            Download.downloadFile( response , "D://excel/" + "excel.xlsx" , fileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  Result.ok(s);
    }

}
