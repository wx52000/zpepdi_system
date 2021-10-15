package com.zpepdi.eureka_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.PowerService;

@RestController
@RequestMapping("power")
public class PowerController {
    private PowerService powerService;

    @Autowired
    public void setPowerService(PowerService powerService){
        this.powerService = powerService;
    }

    @RequestMapping("query")
    public Result query(){
        return Result.ok(powerService.query());
    }

}
