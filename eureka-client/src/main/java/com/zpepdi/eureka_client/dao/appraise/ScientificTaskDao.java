package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScientificTaskDao {

    Map<String,Object> querySurplus(@Param("userId")Integer userId, @Param("id")Integer id);

    void addTask(@Param("userId")Integer userId,@Param("map") Map<String,Object> map);

    void addTaskWorkday(Map<String,Object> map);

    List<Map<String,Object>> queryTask(@Param("userId")Integer userId, @Param("id")Integer id);

    List<Map<String,Object>> getCheckerList(Integer id);

    List<Map<String,Object>> queryTaskBySubmitDate(@Param("userId")Integer userId, @Param("map")Map<String,Object> map);

    void taskSubmit(Map<String,Object> map);

    Map<String,Object> queryProjectByTask(Integer taskId);

}
