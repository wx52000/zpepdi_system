package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping
public interface FrozenWorkdayConfigDao {

    List<Map<String,Object>> query();

    void setProjectFrozen(Map<String,Object> map);

    void setFrozenNumber(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    String queryByUserAndProject(@Param("userId") Integer userId, @Param("projectId") Integer projectId);

    List<Map<String,Object>> queryByUserId(Integer userId);
}
