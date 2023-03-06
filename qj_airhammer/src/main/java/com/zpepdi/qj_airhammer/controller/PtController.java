package com.zpepdi.qj_airhammer.controller;

import com.zpepdi.qj_airhammer.annotation.UserId;
import com.zpepdi.qj_airhammer.result.Result;
import com.zpepdi.qj_airhammer.service.PtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("water")
public class PtController {

    @Autowired
    private PtService ptService;
    @RequestMapping("pt")
    public Result pt(@RequestBody Map<String, Object> map){
        return ptService.pt(map);
    }

    @RequestMapping("ph")
    public Result ph(@RequestBody Map<String, Object> map){
        return ptService.ph(map);
    }

    @RequestMapping("ps")
    public Result ps(@RequestBody Map<String, Object> map){
        return ptService.ps(map);
    }

    @RequestMapping("hs")
    public Result hs(@RequestBody Map<String, Object> map){
        return ptService.hs(map);
    }

    @RequestMapping("jl")
    public Result jl(@RequestBody Map<String, Object> map){
        return ptService.jl(map);
    }
    @RequestMapping("ds")
    public Result ds(@RequestBody Map<String, Object> map){
        return ptService.ds(map);
    }
    @RequestMapping("save")
    public Result save(@UserId Integer userId, @RequestBody Map<String, Object> map){
        return ptService.save(userId,map);
    }
    @RequestMapping("show")
    public Result show(@UserId Integer userId, @RequestBody Map<String, Object> map){
        return ptService.show(userId,map);
    }


}
