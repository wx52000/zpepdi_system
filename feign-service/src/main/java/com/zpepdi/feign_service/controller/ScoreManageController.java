package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.entity.ScoreManage;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ScoreManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("scoreManage")
public class ScoreManageController {
  private ScoreManageService scoreManageService;
  @Autowired
  public void  setScoreManageService(@Qualifier("com.zpepdi.feign_service.service.ScoreManageService") ScoreManageService scoreManageService){
    this.scoreManageService = scoreManageService;
  }

  @RequestMapping("handle")
  public Result handle(@RequestBody ScoreManage scoreManage){
    return scoreManageService.handle(scoreManage);
  }
}
