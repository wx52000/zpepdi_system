package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.UserPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("userPosition")
public class UserPositionController {
    private UserPositionService userPositionService;
    @Autowired
    public void setUserPositionService(@Qualifier("com.zpepdi.feign_service.service.UserPositionService") UserPositionService userPositionService){
        this.userPositionService = userPositionService;
    }

    @RequestMapping("add")
    public Result add(@RequestBody Map map){
        return userPositionService.add(map);
    }
    @RequestMapping("del")
    public Result del(@RequestHeader Integer id){
        return userPositionService.del(id);
    }
}
