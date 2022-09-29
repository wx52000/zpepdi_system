package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProjectTecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projectTec")
public class ProjectTecController {
    private ProjectTecService projectTecService;
    @Autowired
    public void setProjectTecService(@Qualifier("com.zpepdi.feign_service.service.ProjectTecService") ProjectTecService projectTecService){
        this.projectTecService = projectTecService;
    }

    @RequestMapping("query")
    public Result query(@RequestHeader Integer id){
        return projectTecService.query(id);
    }
}
