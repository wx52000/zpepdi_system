package com.zpepdi.eureka_client.dao;

import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.Position;

import java.util.List;
import java.util.Map;

@Repository
public interface PositionDao {
  List<Map> query();

  List<Map> queryByWeight(Integer id);

  void  update(List<Position> list);

}
