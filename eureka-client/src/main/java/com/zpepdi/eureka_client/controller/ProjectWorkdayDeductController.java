package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectWorkdayDeductService;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("deduct")
public class ProjectWorkdayDeductController {
    @Autowired
    private ProjectWorkdayDeductService projectWorkdayDeductService;
    
    @RequestMapping("add")
    public Result add(@UserId Integer id,@RequestBody Map<String, Object> map) {
        return projectWorkdayDeductService.add(id,map);
    }

    @RequestMapping("close")
    public Result close(@RequestHeader("id") Integer id) {
        return projectWorkdayDeductService.close(id);
    }

    @RequestMapping("queryLog")
    public Result queryLog(@RequestHeader("id") Integer id) {
        return projectWorkdayDeductService.queryLog(id);
    }

    @RequestMapping("queryLogId")
    public Result queryLogById(@RequestHeader("id") Integer id) {
        return projectWorkdayDeductService.queryLogById(id);
    }

    @RequestMapping("queryLogByProject")
    public Result queryLogByProject(@RequestHeader("id") Integer id) {
        return projectWorkdayDeductService.queryLogByProject(id);
    }



}
