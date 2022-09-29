package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TaskDeductDao {

    void add(@Param("id")Integer id, @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryLog(Integer id);

    Map<String,Object> queryLogById(Integer id);
}
