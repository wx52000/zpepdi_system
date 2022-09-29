package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ScoreManage;

@Repository
public interface ScoreManageDao {

  void setScoreMange(ScoreManage scoreMange);

  void del(ScoreManage scoreManage);
}
