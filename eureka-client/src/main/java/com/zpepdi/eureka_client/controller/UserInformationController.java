package com.zpepdi.eureka_client.controller;


import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userInformation")
public class UserInformationController {
    @Autowired
    private UserInformationService userInformationService;

    @RequestMapping("query")
    public Result query(){
        return userInformationService.query();
    }
}
