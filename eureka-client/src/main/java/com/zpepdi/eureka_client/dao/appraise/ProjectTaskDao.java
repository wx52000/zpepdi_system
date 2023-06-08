package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ProjectTaskDao {

    Map<String,Object> queryById(Integer id);

    void addTask(@Param("id")Integer id,@Param("map") Map<String, Object> map);

    void update(Map<String,Object> map);

    void taskLog(@Param("id")Integer id, @Param("map")Map<String,Object> map);

    void taskWorkday(Map<String, Object> map);

    void queryByHumanId(Integer id);

    boolean taskExist(String number);

    void  setAdvance(Map<String,Object> map);

    void del(Map<String,Object> map);
}
