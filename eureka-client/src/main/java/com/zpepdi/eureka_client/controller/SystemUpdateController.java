package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.SystemUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemUpdateController {
    @Autowired
    private SystemUpdateService systemUpdateService;

    @RequestMapping("systemLog")
    public Result log(){
        return systemUpdateService.query();
    }
}
