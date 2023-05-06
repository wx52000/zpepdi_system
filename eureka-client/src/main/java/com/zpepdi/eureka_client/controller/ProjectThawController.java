package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectThawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("projectThaw")
public class ProjectThawController {
    @Autowired
    private ProjectThawService projectThawService;

    @RequestMapping("addProjectThaw")
    public Result addProjectThaw(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectThawService.addProjectThaw(userId,map);
    }
}
