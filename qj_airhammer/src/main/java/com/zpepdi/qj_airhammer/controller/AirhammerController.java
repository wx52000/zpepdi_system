package com.zpepdi.qj_airhammer.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpepdi.qj_airhammer.entity.AirHammer;
import com.zpepdi.qj_airhammer.service.AirHammerService;
import com.zpepdi.qj_airhammer.service.PtService;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("airhammer")
public class AirhammerController {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private AirHammerService airHammerService;
    private PtService ptService;

    @Autowired
    public  void  setAirHammerService(AirHammerService airHammerService){
        this.airHammerService = airHammerService;
    }
    @RequestMapping("compute")
    public HttpServletResponse Compute(HttpServletResponse response,@RequestParam("file") MultipartFile file , @RequestParam("hammer") String data) throws IOException, WriteException {
        AirHammer airHammer = JSONObject.parseObject(data, AirHammer.class);
        return airHammerService.compute(response,airHammer,file);
    }


}
