package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScientificTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("scientificTask")
public class ScientificTaskController {
    @Autowired
    private ScientificTaskService scientificTaskService;

    @RequestMapping("querySurplus")
    public Result querySurplus(@UserId Integer userId,@RequestHeader("id") Integer id){
        return scientificTaskService.querySurplus(userId,id);
    }

    @RequestMapping("addTask")
    public Result addTask(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return scientificTaskService.addTask(userId,map);
    }


    @RequestMapping("queryTask")
    public Result queryTask(@UserId Integer userId,@RequestHeader("id") Integer id){
        return scientificTaskService.queryTask(userId,id);
    }

    @RequestMapping("queryTaskBySubmitDate")
    public Result queryTaskBySubmitDate(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return scientificTaskService.queryTaskBySubmitDate(userId,map);
    }

    @RequestMapping("taskSubmit")
    public Result taskSubmit(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return scientificTaskService.taskSubmit(map);
    }
}
