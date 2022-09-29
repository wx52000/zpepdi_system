package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.TaskDeductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("taskDeduct")
public class TaskDeductController {
    @Autowired
    private TaskDeductService taskDeductService;

    @RequestMapping("add")
    public Result add(@UserId Integer id, @RequestBody Map<String,Object> map){
        return taskDeductService.add(id,map);
    }

    @RequestMapping("queryLogById")
    public Result queryLogById(@UserId Integer userId, @RequestHeader("id") Integer id ){
        return taskDeductService.queryLogById(id);
    }
}
