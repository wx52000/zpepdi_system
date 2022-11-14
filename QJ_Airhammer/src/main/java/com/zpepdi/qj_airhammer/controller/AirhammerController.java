package com.zpepdi.qj_airhammer.controller;

import com.zpepdi.qj_airhammer.entity.AirHammer;
import com.zpepdi.qj_airhammer.result.Result;
import com.zpepdi.qj_airhammer.service.AirHammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

public class AirhammerController {

    private AirHammerService airHammerService;
    @Autowired
    public  void  setAirHammerService(AirHammerService airHammerService){
        this.airHammerService = airHammerService;
    }
//    @RequestMapping("compute")
    public Result Compute(@RequestBody AirHammer airhammer){
        return  airHammerService.compute(airhammer);
    }
}
