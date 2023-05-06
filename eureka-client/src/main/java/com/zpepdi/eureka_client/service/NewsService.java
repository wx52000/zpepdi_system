package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface NewsService {

  Result newsCount(Integer id);

  Result queryCountByType(Integer id);

  Result queryByType(Integer id,Integer type);

  Result query(Integer id);

  Result queryLog(Integer id, Integer index);

  Result check(Integer id, List<Map<String,Object>> list, Integer check);

  Result querySelfCheck(Integer id);

  Result queryExtend(Integer userId,Map<String,Object> map);

  Result withdrawOverAll(Map<String,Object> map);

}
