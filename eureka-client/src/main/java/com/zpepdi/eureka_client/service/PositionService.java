package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.Position;
import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface PositionService {

  Result query();

  List<Map> queryByWeight(Integer id);


  Result update(List<Position> position);
}
