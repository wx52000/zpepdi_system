package com.zpepdi.qj_heating.controller;

import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.UserUnitService;
import com.zpepdi.qj_heating.service.jsdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("query")
@CrossOrigin(origins = "*")
public class jsdataController {

    @Autowired
    private jsdataService jsdataservice;

    @RequestMapping("queryTableNames")
    public Result queryTableNames(){
        Result result = jsdataservice.queryTableNames();
        return result;
    }
    @RequestMapping("queryzhcn")
    public Result queryzhcn(){
        Result result = jsdataservice.queryzhcn();
        return result;
    }

    @RequestMapping("queryTabledata")
    public Result queryTabledata(@RequestHeader("tablename") String tablename){
        Result result = jsdataservice.queryTabledata(tablename);
        return result;
    }
    @RequestMapping("queryTablebz")
    public Result queryTablebz(@RequestHeader("tablename") String tablename){
        Result result = jsdataservice.queryTablebz(tablename);
        return result;
    }
}
