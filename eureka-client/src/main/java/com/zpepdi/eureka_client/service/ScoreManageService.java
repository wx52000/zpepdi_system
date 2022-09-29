package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.ScoreManage;
import com.zpepdi.eureka_client.result.Result;

public interface ScoreManageService {

  Result handle(ScoreManage scoreManage);
}
