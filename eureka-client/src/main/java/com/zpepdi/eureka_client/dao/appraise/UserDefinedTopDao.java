package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDefinedTopDao {

    void setUserDefinedTop(@Param("userId")Integer userId,
                           @Param("map")Map<String,Object> map);

    void resetUserDefinedTop(@Param("userId")Integer userId,
                             @Param("map")Map<String,Object> map);
}
