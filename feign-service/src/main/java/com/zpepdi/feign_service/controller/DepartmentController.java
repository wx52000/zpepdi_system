package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.entity.Department;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional(rollbackFor = Exception.class,isolation = Isolation.READ_UNCOMMITTED)
@RestController
@RequestMapping("department")
public class DepartmentController {
    private DepartmentService departmentService;
    @Autowired
    public void setDepartmentService(@Qualifier("com.zpepdi.feign_service.service.DepartmentService") DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @RequestMapping("query")
    public Result query(){
        return departmentService.query();
    }

    @RequestMapping("queryNotUser")
    public Result queryNotUser() {
      return departmentService.queryNotUser();
    }

    @RequestMapping("add")
    public Result add(@RequestBody Department department){
        return departmentService.add(department);
    }
    @RequestMapping("del")
    public Result del(@RequestHeader("id") Integer id){
        return departmentService.del(id);
    }

}
