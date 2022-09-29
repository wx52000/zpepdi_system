package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScientificDao {

    List<Map<String,Object>> query();

    Map<String,Object> queryById(@Param("userId")Integer userId,@Param("id") Integer id);

    void addScientific(@Param("userId")Integer userId, @Param("map")Map<String,Object> map);


}
