package com.zpepdi.qj_heating.controller;

import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.ThicknessSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Thickness")
@CrossOrigin(origins = "*")
public class ThicknessController {

    @Autowired
    private ThicknessSerice thicknessSerice;


    @RequestMapping("queryRank")
    public Result queryRank(){
        Result result = thicknessSerice.queryRank();
        return result;
    }
    @RequestMapping("queryjiezhi")
    public Result queryjiezhi(){
        Result result = thicknessSerice.queryjiezhi();
        return result;
    }
}
