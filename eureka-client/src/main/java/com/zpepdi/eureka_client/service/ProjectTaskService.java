package com.zpepdi.eureka_client.service;


import com.zpepdi.eureka_client.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ProjectTaskService {

    Result addTask(Integer id, Map<String,Object> map);

    Result update(Integer id, Map<String,Object> map);

    Result taskWorkday(Integer id, Map<String, Object> map);

    Result queryByHumanId(Integer id);

    Result setAdvance(Map<String,Object> map);

    Result del(Map<String,Object> map);
}
