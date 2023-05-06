package com.zpepdi.audit_service.controller;

import com.zpepdi.audit_service.annotation.RequestUser;
import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;
import com.zpepdi.audit_service.service.CcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("cc")
public class CcController {

    @Autowired
    private CcService ccService;

    @RequestMapping("addCc")
    public Result addCc(@RequestUser User user, @RequestBody Map<String,Object> map){
        return ccService.addCc(user,map);
    }

    @RequestMapping("queryBySelf")
    public Result queryBySelf(@RequestUser User user){
        return ccService.queryBySelf(user);
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader("id") Integer id){
        return ccService.queryById(id);
    }

    @RequestMapping("delCc")
    public Result delCc(@RequestHeader("id") Integer id){
        return ccService.delCc(id);
    }
}
