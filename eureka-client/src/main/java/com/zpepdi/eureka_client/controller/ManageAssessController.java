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

    @RequestMapping("setAssessRemark")
    public Result setAssessRemark(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return manageAssessService.setAssessRemark(userId,map);
    }

    @RequestMapping("queryAssessSum")
    public Result queryAssessSum(@UserId Integer userId){
        return manageAssessService.queryAssessSum(userId);
    }

}
