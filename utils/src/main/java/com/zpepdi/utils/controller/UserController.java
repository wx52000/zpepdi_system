package com.zpepdi.utils.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/cc")
    public String text1(){
        return "成功访问";
    }
}
