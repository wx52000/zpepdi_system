package com.zpepdi.eureka_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectTecService;

@RestController
@RequestMapping("projectTec")
public class ProjectTecController {
    private ProjectTecService projectTecService;
    @Autowired
    public void setProjectTecService(ProjectTecService projectTecService){
        this.projectTecService = projectTecService;
    }

    @RequestMapping("query")
    public Result query(@RequestHeader Integer id){
        return Result.ok(projectTecService.query(id));
    }
}
