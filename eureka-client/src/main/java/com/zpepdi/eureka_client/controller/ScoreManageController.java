package com.zpepdi.eureka_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.ScoreManage;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScoreManageService;

@RestController
@RequestMapping("scoreManage")
public class ScoreManageController {
  private ScoreManageService scoreManageService;
  @Autowired
  public void  setScoreManageService(ScoreManageService scoreManageService){
    this.scoreManageService = scoreManageService;
  }

  @RequestMapping("handle")
  public Result handle(@RequestBody ScoreManage scoreManage){
    scoreManageService.handle(scoreManage);
    return Result.ok();
  }
}
