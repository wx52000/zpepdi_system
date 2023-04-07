package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectRelativeContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("pRelativeC")
public class ProjectRelativeContractController {
    @Autowired
    private ProjectRelativeContractService projectRelativeContractService;

    @RequestMapping("relativeContract")
    public Result relativeContract(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectRelativeContractService.addProjectRelativeContract(userId,map);
    }

    @RequestMapping("queryRelativeLog")
    public Result queryRelativeLog(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectRelativeContractService.queryRelativeLog(map);
    }
}
