package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Technology;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.TechnologyService;

@RestController
@RequestMapping("technology")
public class TechnologyController {
    private TechnologyService technologyService;
    @Autowired
    public void setTechnologyService(TechnologyService technologyService){
        this.technologyService = technologyService;
    }

    @RequestMapping("queryAll")
    public Result queryAll(){
        return Result.ok(technologyService.queryAll());
    }

    @RequestMapping("query")
    public Result query(@RequestHeader Integer id){
        return Result.ok(technologyService.query(id));
    }

    @RequestMapping("queryByUserId")
    public Result queryByUserId(@UserId Integer id){
        return Result.ok(technologyService.queryByUserId(id));
    }

    @RequestMapping("querySelf")
    public Result querySelf(@UserId Integer id){
        return Result.ok(technologyService.querySelf(id));
    }

    @RequestMapping("queryNotUser")
    public Result queryNotUser() {
      return Result.ok(technologyService.queryNotUser());
    }
//打分专业
    @RequestMapping("evaluate")
    public Result evaluate(@UserId Integer userId) {
      return Result.ok(technologyService.evaluate(userId));
    }

    @RequestMapping("add")
    public Result add(@RequestBody Technology technology){
        technologyService.add(technology);
        return Result.ok();
    }

    @RequestMapping("del")
    public Result del(@RequestHeader Integer id){
        technologyService.del(id);
        return Result.ok();
    }

    @RequestMapping("queryToSelected")
    public Result queryToSelected(){
        return Result.ok(technologyService.queryToSelected());
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestBody Project project){
        return Result.ok(technologyService.queryById(project));
    }

    @RequestMapping("queryByPrincipal")
    public Result queryByPrincipal(@UserId Integer userId,@RequestHeader Integer id){
        return Result.ok(technologyService.queryByPrincipal(userId,id));
    }
}
