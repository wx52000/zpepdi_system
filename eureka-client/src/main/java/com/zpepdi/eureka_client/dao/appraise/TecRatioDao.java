package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.TecRatio;

import java.util.Map;

@Repository
public interface TecRatioDao {

  Map queryByTec(Integer id);

  void setRatioByTec(TecRatio tec);
}
