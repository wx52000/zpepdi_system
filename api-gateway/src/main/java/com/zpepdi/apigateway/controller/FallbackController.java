package com.zpepdi.apigateway.controller;


import com.zpepdi.apigateway.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class FallbackController {

    @RequestMapping("/defaultfallback")
    public Result defaultfallback(){
        return Result.build(440);
    }
}
