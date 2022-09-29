package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("power")
public class PowerController {
    private PowerService powerService;

    @Autowired
    public void setPowerService(@Qualifier("com.zpepdi.feign_service.service.PowerService") PowerService powerService){
        this.powerService = powerService;
    }

    @RequestMapping("query")
    public Result query(){
        return powerService.query();
    }

}
