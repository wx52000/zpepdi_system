package com.zpepdi.eureka_client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.PositionDao;
import com.zpepdi.eureka_client.entity.Position;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.PositionService;

import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImpl implements PositionService {
  private PositionDao positionDao;
  @Autowired
  private void setPositionDao(PositionDao positionDao){
    this.positionDao = positionDao;
  }

  @Override
  public Result query() {
    return Result.ok(positionDao.query());
  }

  @Override
  public List<Map> queryByWeight(Integer id) {
    return positionDao.queryByWeight(id);
  }

  @Override
  public Result update(List<Position> position) {
    try {
      positionDao.update(position);
      return  Result.ok();
    }catch (Exception e){
      e.printStackTrace();
      return  Result.build(1000,"修改失败");
    }
  }
}
