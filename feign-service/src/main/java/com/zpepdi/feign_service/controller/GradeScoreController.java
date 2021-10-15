package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.entity.GradeScore;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.GradeScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gradeScore")
public class GradeScoreController {
    private GradeScoreService gradeScoreService;
    @Autowired
    public  void  setGradeScoreService(@Qualifier("com.zpepdi.feign_service.service.GradeScoreService") GradeScoreService gradeScoreService){
        this.gradeScoreService = gradeScoreService;
    }


    @RequestMapping("manage")
    public Result manage(@RequestBody GradeScore gradeScore){
        return gradeScoreService.manage(gradeScore);
    }


    @RequestMapping("queryTec")
    public Result queryTec(@RequestHeader("id") Integer id){
        return gradeScoreService.queryTec(id);
    }

    @RequestMapping("queryDep")
    public Result queryDep(@RequestHeader("id") Integer id){
        return gradeScoreService.queryDep(id);
    }

}
