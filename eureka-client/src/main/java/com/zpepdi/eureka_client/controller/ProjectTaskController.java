package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("task")
public class ProjectTaskController {
    @Autowired
    private ProjectTaskService projectTaskService;


    @RequestMapping("/addTask")
    public Result addTask(@UserId Integer id, @RequestBody Map<String,Object> map){
        return projectTaskService.addTask(id,map);
    }

    @RequestMapping("/update")
    public Result taskComplete(@UserId Integer id, @RequestBody Map<String,Object> map){
        return projectTaskService.update(id,map);
    }

    @RequestMapping("/taskWorkday")
    public Result taskWorkday(@UserId Integer id, @RequestBody Map<String,Object> map){
        return projectTaskService.taskWorkday(id,map);
    }

    @RequestMapping("setAdvance")
    public Result setAdvance(@RequestBody Map<String,Object> map){
        return projectTaskService.setAdvance(map);
    }


    @RequestMapping("del")
    public Result del(@RequestBody Map<String,Object> map){
        return projectTaskService.del(map);
    }
}
