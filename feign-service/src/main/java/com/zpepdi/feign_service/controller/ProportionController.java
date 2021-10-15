package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.entity.Proportion;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProportionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proportion")
public class ProportionController {

    private ProportionService proportionService;


    @Autowired
    public void setProportionService(@Qualifier("com.zpepdi.feign_service.service.ProportionService") ProportionService proportionService){
        this.proportionService = proportionService;
    }

    @RequestMapping("add")
    private Result add(@RequestBody Proportion proportion){
        return proportionService.add(proportion);
    }

    @RequestMapping("queryLastTime")
    private Result queryLastTime(@RequestBody Proportion proportion){
        return proportionService.queryLastTime(proportion);
    }

    @RequestMapping("queryLastTwoTimes")
    private Result queryLastTwoTime(@RequestBody Proportion proportion){
        return proportionService.queryLastTwoTime(proportion);
    }
}
