package com.zpepdi.qj_airhammer.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zpepdi.qj_airhammer.annotation.UserId;
import com.zpepdi.qj_airhammer.entity.AirHammer;
import com.zpepdi.qj_airhammer.service.FlowService;
import com.zpepdi.qj_airhammer.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
@RestController
@RequestMapping("flow")
public class FlowController {
    @Autowired
    private FlowService flowService;

    @RequestMapping("compute")
    public Result flow(@RequestBody Map<String, Object> map){
        return flowService.compute(map);
    }

    @RequestMapping("judge")
    public Result judge(@RequestBody Map<String, Object> map){
        return flowService.judge(map);
    }

    @RequestMapping("export")
    public HttpServletResponse export(HttpServletResponse response, @RequestBody String json) throws IOException {
        return flowService.export(response,json);
    }
    @RequestMapping("export1")
    public HttpServletResponse export1(HttpServletResponse response, @RequestBody String json) throws IOException {
        return flowService.export1(response,json);
    }
    @RequestMapping("export2")
    public HttpServletResponse export2(HttpServletResponse response, @RequestBody String json) throws IOException {
        return flowService.export2(response,json);
    }


    @RequestMapping("save")
    public Result save(@UserId Integer userId, @RequestBody Map<String, Object> map) throws UnsupportedEncodingException {
        return flowService.save(userId,map);
    }

    @RequestMapping("upload")
    public HttpServletResponse upload(HttpServletResponse response,@UserId Integer userId, @RequestParam("file") MultipartFile file ,HttpServletRequest request) throws Exception {
        return flowService.upload(response,userId,file,request);
    }

//    @RequestMapping("mpa")
//    public Result mpa(@RequestBody Map<String, Object> map){
//        return flowService.mpa(map);
//    }
//
//
//    @RequestMapping("temperature")
//    public Result temperature(@RequestBody Map<String, Object> map){
//        return flowService.temperature(map);
//    }
}
