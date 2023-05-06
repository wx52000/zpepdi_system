package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ManageAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("manageAssess")
public class ManageAssessController {

    @Autowired
    private ManageAssessService manageAssessService;

    @RequestMapping("setUserAssess")
    public Result setUserAssess(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return manageAssessService.setUserAssess(userId,map);
    }

    @RequestMapping("grantAuthority")
    public Result grantAuthority(@RequestBody Map<String,Object> map){
        return manageAssessService.grantAuthority(map);
    }

    @RequestMapping("setAssessRemark")
    public Result setAssessRemark(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return manageAssessService.setAssessRemark(userId,map);
    }

    @RequestMapping("setUserAssessConfirm")
    public Result setUserAssessConfirm(@UserId Integer userId){
        return manageAssessService.setUserAssessConfirm(userId);
    }

    @RequestMapping("setUserAssessTo0")
    public Result setUserAssessTo0(@UserId Integer userId){
        return manageAssessService.setUserAssessTo0(userId);
    }

    @RequestMapping("setUserAssessAvg")
    public Result setUserAssessAvg(@UserId Integer userId){
        return manageAssessService.setUserAssessAvg(userId);
    }

    @RequestMapping("queryAssessSum")
    public Result queryAssessSum(@UserId Integer userId){
        return manageAssessService.queryAssessSum(userId);
    }
}
