package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectRelativeContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("pRelativeC")
public class ProjectRelativeContractController {
    @Autowired
    private ProjectRelativeContractService projectRelativeContractService;

    @RequestMapping("queryByProjectId")
    public Result queryByProjectId(@UserId Integer userId, @RequestHeader Integer id){
        return projectRelativeContractService.queryByProjectId(id);
    }

    @RequestMapping("queryNotSubmitByUserId")
    public Result queryNotSubmitByUserId(@UserId Integer userId){
        return projectRelativeContractService.queryNotSubmitByUserId(userId);
    }
    @RequestMapping("relativeContract")
    public Result relativeContract(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectRelativeContractService.addProjectRelativeContract(userId,map);
    }

    @RequestMapping("queryRelativeLog")
    public Result queryRelativeLog(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectRelativeContractService.queryRelativeLog(map);
    }

    @RequestMapping("submitRelative")
    public Result submitRelative(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectRelativeContractService.submitRelative(map);
    }

    @RequestMapping("delRelative")
    public Result delRelative(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectRelativeContractService.delRelative(map);
    }
}
