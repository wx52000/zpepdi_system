package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.DeclareDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("declareDay")
@RestController
public class DeclareDayController {
    @Autowired
    private DeclareDayService declareDayService;

    @RequestMapping("declareDay")
    public Result declareDay(){
        return Result.ok(declareDayService.declareDay());
    }

    @RequestMapping("setDeclareDay")
    public Result setDeclareDay(@RequestHeader("day") Integer day){
        return declareDayService.setDeclareDay(day);
    }

    @RequestMapping("declareDelay")
    public Result declareDelay(){
        return Result.ok(declareDayService.declareDelay());
    }

    @RequestMapping("setDeclareDelay")
    public Result setDeclareDelay(@RequestHeader("day") Integer day){
        return declareDayService.setDeclareDelay(day);
    }
}
