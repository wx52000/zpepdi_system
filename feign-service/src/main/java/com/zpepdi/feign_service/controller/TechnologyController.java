package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.Technology;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("technology")
public class TechnologyController {
    private TechnologyService technologyService;
    @Autowired
    public void setTechnologyService(@Qualifier("com.zpepdi.feign_service.service.TechnologyService") TechnologyService technologyService){
        this.technologyService = technologyService;
    }

    @RequestMapping("query")
    public Result query(@RequestHeader Integer id){
        return technologyService.query(id);
    }

    @RequestMapping("queryNotUser")
    public Result queryNotUser() {
      return technologyService.queryNotUser();
    }

    @RequestMapping("evaluate")
    public Result evaluate(@UserId Integer id) {
      return technologyService.evaluate(id);
    }

    @RequestMapping("add")
    public Result add(@RequestBody Technology technology){
        return technologyService.add(technology);
    }

    @RequestMapping("del")
    public Result del(@RequestHeader Integer id){
        return technologyService.del(id);
    }

    @RequestMapping("queryToSelected")
    public Result queryToSelected(){
        return technologyService.queryToSelected();
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestBody Project project){
        return technologyService.queryById(project);
    }
}
