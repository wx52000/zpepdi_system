package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.CheckerService;
import com.zpepdi.eureka_client.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("checker")
@RestController
public class CheckerController {

    @Autowired
    private CheckerService checkerService;

    @RequestMapping("del")
    public Result del(@RequestBody Map<String, Object> map) {
        return checkerService.del(map);
    }

    @RequestMapping("set")
    public Result set(@RequestBody Map<String, Object> map) {
        return checkerService.set(map);
    }

    @RequestMapping("queryDepChecker")
    public Result queryDepChecker(){
        return checkerService.queryDepChecker();
    }

    @RequestMapping("delDepChecker")
    public Result delDepChecker(@RequestBody Map<String,Object> map){
        return checkerService.delDepChecker(map);
    }

    @RequestMapping("setDepChecker")
    public Result setDepChecker(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return checkerService.setDepChecker(userId, map);
    }

}
