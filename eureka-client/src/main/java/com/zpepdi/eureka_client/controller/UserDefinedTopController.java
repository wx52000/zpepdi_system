package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.UserDefinedTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("userDefinedTop")
public class UserDefinedTopController {
    @Autowired
    private UserDefinedTopService userDefinedTopService;

    @RequestMapping("setUserDefinedTop")
    public Result setUserDefinedTop(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return userDefinedTopService.setUserDefinedTop(userId,map);
    }

    @RequestMapping("resetUserDefinedTop")
    public Result resetUserDefinedTop(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return userDefinedTopService.resetUserDefinedTop(userId,map);
    }

}
