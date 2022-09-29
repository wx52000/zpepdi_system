package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.dao.zjepdi.ZJEPDIDataTransmissionDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.DataTransmissionService;
import com.zpepdi.eureka_client.service.ScientificSystemService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class ScientificSystemController {
    @Autowired
    private ScientificSystemService scientificSystemService;
    @Autowired
    private DataTransmissionService dataTransmissionService;


    @RequestMapping("downProduceExcel")
    public HttpServletResponse downProduceExcel(HttpServletResponse response) throws Exception {
        return scientificSystemService.down(response);
    }

    @RequestMapping("fdDataTransmit")
    public Result fdDataTransmit(){
        return scientificSystemService.fdDataTransmit();
    }

    @RequestMapping("produceDataTransmit")
    public Result produceDataTransmit(@RequestBody Map<String,Object> map){
        return scientificSystemService.produceDataTransmit(map);
    }


}
