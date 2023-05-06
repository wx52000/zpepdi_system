package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.FrozenWorkdayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("frozenWorkdayConfig")
public class FrozenWorkdayConfigController {
    @Autowired
    private FrozenWorkdayConfigService frozenWorkdayConfigService;

    @RequestMapping("query")
    public Result query(){
        return frozenWorkdayConfigService.query();
    }

    @RequestMapping("setProjectFrozen")
    public Result setProjectFrozen(@RequestBody Map<String,Object> map){
        return frozenWorkdayConfigService.setProjectFrozen(map);
    }

    @RequestMapping("setFrozenNumber")
    public Result setFrozenNumber(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return frozenWorkdayConfigService.setFrozenNumber(userId,map);
    }

    @RequestMapping("queryByUserId")
    public Result queryByUserId(@UserId Integer userId){
        return frozenWorkdayConfigService.queryByUserId(userId);
    }

}
