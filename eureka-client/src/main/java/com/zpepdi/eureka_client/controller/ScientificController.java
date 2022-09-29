package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScientificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("scientific")
public class ScientificController {
    @Autowired
    private ScientificService scientificService;

    @RequestMapping("query")
    public Result query() {
        return scientificService.query();
    }

    @RequestMapping("queryById")
    public Result queryById(@UserId Integer userId, @RequestHeader("id")Integer id) {
        return scientificService.queryById(userId,id);
    }

    @RequestMapping("addScientific")
    public Result addScientific(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return scientificService.addScientific(userId,map);
    }
}
