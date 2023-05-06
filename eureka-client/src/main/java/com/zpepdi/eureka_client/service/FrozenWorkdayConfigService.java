package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FrozenWorkdayConfigService {

    Result query();

    Result setProjectFrozen(Map<String, Object> map);

    Result setFrozenNumber(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    Result queryByUserAndProject(Integer userId,Integer projectId);

    Result queryByUserId(Integer userId);
}
