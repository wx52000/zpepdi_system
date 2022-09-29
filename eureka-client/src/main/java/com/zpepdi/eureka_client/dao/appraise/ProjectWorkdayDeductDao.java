package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectWorkdayDeductDao {

    void add(@Param("id")Integer id, @Param("map")Map<String,Object> map);

    void close(Integer id);

    List<Map<String,Object>> queryLog(Integer id);

    Map<String,Object> queryLogById(Integer id);

    List<Map<String,Object>> queryLogByProject(Integer id);
}
