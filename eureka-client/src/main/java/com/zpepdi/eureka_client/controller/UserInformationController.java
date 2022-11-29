package com.zpepdi.eureka_client.controller;


import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("userInformation")
public class UserInformationController {
    @Autowired
    private UserInformationService userInformationService;

    @RequestMapping("query")
    public Result query(){
        return userInformationService.query();
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader("id") Integer id){
        return userInformationService.queryById(id);
    }


    @RequestMapping("queryByManage")
    public Result queryByManage(@UserId Integer id){
        return userInformationService.queryByManage(id);
    }

    @RequestMapping("reSet")
    public Result reSet(@RequestBody Map<String,Object> map){
        return userInformationService.insert(map);
    }
}
