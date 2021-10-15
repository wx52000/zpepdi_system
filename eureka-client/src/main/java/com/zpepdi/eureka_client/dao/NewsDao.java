package com.zpepdi.eureka_client.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NewsDao {

  Integer newsCount(Integer id);

  List<Map> query(Integer id);
}
