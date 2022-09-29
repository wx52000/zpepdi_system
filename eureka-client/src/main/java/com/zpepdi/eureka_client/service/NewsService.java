package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface NewsService {

  Result newsCount(Integer id);

  Result query(Integer id);

  Result queryLog(Integer id, String date);

  Result check(Integer id, List<Map<String,Object>> list, Integer check);
}
