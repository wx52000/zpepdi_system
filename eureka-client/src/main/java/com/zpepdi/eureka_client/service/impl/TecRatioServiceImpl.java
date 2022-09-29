package com.zpepdi.eureka_client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zpepdi.eureka_client.dao.appraise.TecRatioDao;
import com.zpepdi.eureka_client.entity.TecRatio;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.TecRatioService;

@Service
@Transactional
public class TecRatioServiceImpl implements TecRatioService {
  private TecRatioDao tecRatioDao;
  @Autowired
  public void setTecRatioDao(TecRatioDao tecRatioDao){
    this.tecRatioDao = tecRatioDao;
  }

  @Override
  public Result queryByTec(Integer id) {
    return Result.ok(tecRatioDao.queryByTec(id));
  }

  @Override
  public Result setRatioByTec(TecRatio tecRatio) {
    tecRatioDao.setRatioByTec(tecRatio);
    return Result.ok();
  }
}
