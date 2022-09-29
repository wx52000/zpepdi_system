package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @RequestMapping("query")
    public Result query(){
        return officeService.query();
    }


}
