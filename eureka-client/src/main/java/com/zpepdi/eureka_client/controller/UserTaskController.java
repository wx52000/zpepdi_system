package com.zpepdi.eureka_client.controller;

//项目角色 - 任务数量

import com.zpepdi.eureka_client.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userTask")
public class UserTaskController {

    @RequestMapping("query")
    public Result query(){
        return null;
    }
}
