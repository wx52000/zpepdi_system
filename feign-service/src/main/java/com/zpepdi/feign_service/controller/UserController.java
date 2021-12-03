package com.zpepdi.feign_service.controller;


import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.entity.UserOut;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("user")
public class  UserController {
  @Qualifier("com.zpepdi.feign_service.service.UserService")
  @Autowired
    private UserService userService;

    @RequestMapping("add")
    public Result add(@RequestBody User user){
        return userService.add(user);
    }

    @RequestMapping("del")
    public Result del(@RequestHeader Integer id){
        return userService.del(id);
    }

    @RequestMapping("upd")
    public Result upd(@RequestBody Map<String,Object> user){
        return userService.upd(user);
    }

    @RequestMapping("pwdReset")
    public Result pwdReset(@RequestHeader Integer id){
      return userService.pwdReset(id);
    }
    @RequestMapping("query")
    public Result query(@RequestBody User user){
        return userService.query(user);
    }

    @RequestMapping("queryById")
    public Result queryById(@UserId Integer id){
      return userService.queryById(id);
    }

    @RequestMapping("information")
    public Result information(@UserId Integer userId){
      return userService.information(userId);
    }

    @RequestMapping("workdayLogById")
    public Result workdayLogById(@UserId Integer userId){
      return userService.workdayLogById(userId);
    }

    @RequestMapping("workdayLog")
    public Result workdayLog(@RequestBody Map<String,Object> map){
      return userService.workdayLog(map);
    }

    @RequestMapping("workday")
    public Result workday(@RequestHeader("queryDate") String queryDate){
      return  userService.workday(queryDate);
    }

    @RequestMapping("queryToupd")
    public Result queryToupd(@RequestHeader Integer id){
      return userService.queryToupd(id);
    }

    @RequestMapping("queryByTid")
    public Result queryByTid(@RequestHeader Integer id){
        return userService.queryByTid(id);
    }

    @RequestMapping("queryNotSelf")
    public Result queryNotSelf(@RequestBody User user){
      return userService.queryNotSelf(user);
    }

    @RequestMapping("queryToScore")
    public Result queryToScore(@UserId Integer userId,@RequestBody Map<String,Object> map){
      return userService.queryToScore(userId,map);
    }

    @RequestMapping("queryScoreList")
    public Result queryScoreList(@RequestBody User user){
      return userService.queryScoreList(user);
    }

    @RequestMapping("queryNotScore")
    public Result queryNotScore(@RequestBody User user){
        return userService.queryNotScore(user);
    }

    @RequestMapping("queryAppriseAll")
    public Result queryAppriseAll(){
      return userService.queryAppriseAll();
    }
    @RequestMapping("queryAppraise")
    public Result queryAppraise(@RequestBody User user){
      return userService.queryAppraise(user);
    }

    @RequestMapping("queryNotAppraise")
    public Result queryNotAppraise(@RequestBody User user){
      return userService.queryNotAppraise(user);
    }

    @RequestMapping("queryNotScored")
    public Result queryNotScored(@RequestBody User user){
      return userService.queryNotScored(user);
    }

    @RequestMapping("queryNotTecApp")
    public Result queryNotTecApp(@RequestBody User user){
      return userService.queryNotTecApp(user);
    }

    @RequestMapping("queryByTec")
    public Result queryByTec(@RequestHeader Integer id){
        return userService.queryByTec(id);
    }

    @RequestMapping("queryByName")
    public Result queryByName(@RequestBody User user){
        return userService.queryByName(user);
    }

    @RequestMapping("paw")
    public Result paw(@UserId Integer id,@RequestBody User user){
        return userService.paw(id,user);
    }
    //普通树
    @RequestMapping("userAll")
    public Result userAll(@RequestHeader Integer mode){
      return userService.userAll(mode);
    }
    //评价管理树，包含是否可评价状态
    @RequestMapping("userAllAndState")
    public Result userAllAndState(@RequestHeader Integer id){
      return userService.userAllAndState(id);
    }
    //主设人选择树，包含主设人分组
    @RequestMapping("userAllAndGroup")
    public Result userAllAndGroup(@RequestHeader Integer id,@RequestHeader Integer mode){
      return userService.userAllAndGroup(id,mode);
    }

  @RequestMapping("excel")
  public Result personExcel(HttpServletRequest request , HttpServletResponse response) {
      return Result.ok();
  }

}
