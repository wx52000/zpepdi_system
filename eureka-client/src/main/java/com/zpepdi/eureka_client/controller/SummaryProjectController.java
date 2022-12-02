package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.SummaryProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("summaryProject")
public class SummaryProjectController {
    @Autowired
    private SummaryProjectService summaryProjectService;

    @RequestMapping("setSummaryProject")
    public Result setSummaryProject(@UserId Integer userId,
                                           @RequestBody Map<String,Object> map){
        return summaryProjectService.setSummaryProject(userId,map);
    }

    @RequestMapping("query")
    public Result query(@UserId Integer userId){
        return summaryProjectService.query(userId);
    }

    @RequestMapping("queryByHandler")
    public Result queryByHandler(@UserId Integer userId){
        return summaryProjectService.queryByHandler(userId);
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader("id") Integer userId){
        return summaryProjectService.queryById(userId);
    }

    @RequestMapping("queryRoleById")
    public Result queryRoleById(@RequestHeader("id") Integer userId){
        return summaryProjectService.queryRoleById(userId);
    }



}
