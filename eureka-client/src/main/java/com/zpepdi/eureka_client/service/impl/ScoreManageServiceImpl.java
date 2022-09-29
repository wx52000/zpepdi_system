package com.zpepdi.eureka_client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zpepdi.eureka_client.dao.appraise.ScoreManageDao;
import com.zpepdi.eureka_client.entity.ScoreManage;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScoreManageService;

@Service
@Transactional
public class ScoreManageServiceImpl implements ScoreManageService {
  private ScoreManageDao scoreManageDao;
  @Autowired
  public void setScoreManageDao(ScoreManageDao scoreManageDao){
    this.scoreManageDao = scoreManageDao;
  }
  @Override
  public Result handle(ScoreManage scoreManage) {
    scoreManage.setScoredId(scoreManage.getScoredId().substring(
      scoreManage.getScoredId().lastIndexOf("-")+1));
    if (scoreManage.getState() == null){
      scoreManageDao.del(scoreManage);
    }else {
      scoreManageDao.setScoreMange(scoreManage);
    }
    return Result.ok();
  }
  
}
