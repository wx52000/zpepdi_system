package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserDefinedTopService {

    Result setUserDefinedTop(@Param("userId")Integer userId,
                             @Param("map") Map<String,Object> map);

    Result resetUserDefinedTop(@Param("userId")Integer userId,
                             @Param("map")Map<String,Object> map);
}
