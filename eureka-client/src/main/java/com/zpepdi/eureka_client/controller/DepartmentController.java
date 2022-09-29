package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.Department;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.DepartmentService;

@Transactional(rollbackFor = Exception.class,isolation = Isolation.READ_UNCOMMITTED)
@RestController
@RequestMapping("department")
public class DepartmentController {
    private DepartmentService departmentService;
    @Autowired
    public void setDepartmentService(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @RequestMapping("query")
    public Result query(){
        return Result.ok(departmentService.query());
    }

    @RequestMapping("queryNotUser")
    public Result queryNotUser() {
      return Result.ok(departmentService.queryNotUser());
    }

    @RequestMapping("add")
    public Result add(@RequestBody Department department){
        departmentService.add(department);
        return Result.ok();
    }
    @RequestMapping("del")
    public Result del(@RequestHeader Integer id){
        departmentService.del(id);
        return Result.ok();
    }

    @RequestMapping("queryByProjectAll")
    public Result queryByProjectAll(@UserId Integer userId, @RequestHeader Integer id){
        return departmentService.queryByProjectAll(userId,id);
    }


    @RequestMapping("queryByProject")
    public Result queryByProject(@UserId Integer userId, @RequestHeader Integer id){
        return departmentService.queryByProjectId(userId,id);
    }

    @RequestMapping("queryCheckerAndDep")
    public Result queryCheckerAndDep(){
        return departmentService.queryCheckerAndDep();
    }

    @RequestMapping("queryManageSurplus")
    public Result queryManageSurplus(@RequestHeader Integer id){
        return departmentService.queryManageSurplus(id);
    }

}
