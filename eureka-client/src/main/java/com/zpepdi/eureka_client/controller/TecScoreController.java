package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.PartParam;
import com.zpepdi.eureka_client.entity.TecScore;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.TecScoreService;
import com.zpepdi.eureka_client.service.UserService;
import com.zpepdi.eureka_client.tools.Download;
import com.zpepdi.eureka_client.excel.ExcelProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("tecScore")
public class TecScoreController {

    private TecScoreService tecScoreService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
      this.userService = userService;
    }
    @Autowired
    public void setTecScoreService(TecScoreService tecScoreService){
        this.tecScoreService = tecScoreService;
    }

    @RequestMapping("queryByGradeId")
    public Result queryByGradeId(@RequestHeader Integer id){
        return Result.ok(tecScoreService.queryByGradeId(id));
    }

    @RequestMapping("queryByScoreId")
    public Result queryByScoreId(@RequestBody User user){
      return Result.ok(tecScoreService.queryByScoreId(user));
    }

    //专业评价
    @RequestMapping("appraise")
    public Result appraise(@UserId Integer id, @RequestBody TecScore tecScore){
        tecScoreService.appraise(id,tecScore);
        return Result.ok(0);
    }

    @RequestMapping("queryScore")
    public Result queryScore(@RequestBody User user){
        return Result.ok(tecScoreService.queryScore(user));
    }

    @RequestMapping("query")
    public Result query(@RequestBody User user){
        return Result.ok(tecScoreService.query(user));

    }

    @RequestMapping("detail")
    public Result detail(HttpServletResponse response,HttpServletRequest request){
      String s= "";
      try {
        User user = new User();
        user.setThisMonth(Integer.valueOf(request.getParameter("month")));
        ExcelProperty excelProperty = new ExcelProperty();
        List<String> userName = userService.queryGrade();
        Future<String> future = excelProperty
          .personalDetailsExcel(userName,tecScoreService.detail(user,userName));
        s = future.get();
        String fileName = user.getThisMonth() + "月个人评分详情表.xlsx";
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
      fileName = partParam.getMonth() + "月专业评分详情表.xlsx";
      ExcelProperty excelProperty = new ExcelProperty();
      Future<String> future = excelProperty
        .tecPartExcel(tecScoreService.part(partParam.getList(),partParam.getMonth()),fileName);
      s = future.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return  Result.ok(fileName);
  }


  @RequestMapping("excel")
    public Result excel(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String s = "";
        try {
            User user = new User();
            user.setId(Integer.valueOf(request.getParameter("id")));
            user.setThisMonth(Integer.valueOf(request.getParameter("month")));
            ExcelProperty excelProperty = new ExcelProperty();
            Future<String> future = excelProperty.technologyExcel(tecScoreService.excel(user));
            s = future.get();
            String fileName = user.getThisMonth() + "月专业得分汇总表.xlsx";
            Download.downloadFile( response , "D://excel/" + "excel1.xlsx" , fileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }
}
