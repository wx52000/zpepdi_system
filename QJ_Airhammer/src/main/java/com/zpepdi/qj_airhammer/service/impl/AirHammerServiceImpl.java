package com.zpepdi.qj_airhammer.service.impl;

import com.zpepdi.qj_airhammer.entity.AirHammer;
import com.zpepdi.qj_airhammer.result.Result;
import com.zpepdi.qj_airhammer.service.AirHammerService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AirHammerServiceImpl implements AirHammerService {
    @Override
    public Result compute(AirHammer airHammer) {

        BigDecimal num1=new BigDecimal("1.05");
        BigDecimal num2=new BigDecimal("1000");
        BigDecimal num3=new BigDecimal("3600");
        BigDecimal big1=(airHammer.getFlow().multiply(airHammer.getLength())).multiply(num1);
        BigDecimal big2=(num2.multiply(num3)).multiply(airHammer.getTime());
        BigDecimal big3=big1.divide(big2);
        String a =big3.setScale(0,BigDecimal.ROUND_HALF_UP).toString();
        int b=Integer.parseInt(a)*2;
        Map<String,Object> map=new HashMap<>();
        map.put("F",big3);
        map.put("quzheng",a);
        map.put("quzhengx2",b);
        return Result.ok(map);


    }


}
