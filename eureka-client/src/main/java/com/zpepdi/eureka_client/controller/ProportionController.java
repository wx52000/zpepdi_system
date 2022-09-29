package com.zpepdi.eureka_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.Proportion;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProportionService;

@RestController
@RequestMapping("proportion")
public class   ProportionController {

    private ProportionService proportionService;


    @Autowired
    public void setProportionService(ProportionService proportionService){
        this.proportionService = proportionService;
    }

    @RequestMapping("add")
    private Result add(@RequestBody Proportion proportion){
        proportionService.add(proportion);
        return Result.ok();
    }

    @RequestMapping("queryLastTime")
    private Result queryLastTime(@RequestBody Proportion proportion){
        return Result.ok(proportionService.queryLastTime(proportion));
    }

    @RequestMapping("queryLastTwoTimes")
    private Result queryLastTwoTime(@RequestBody Proportion proportion){
        return Result.ok(proportionService.queryLastTwoTimes(proportion));
    }
}
