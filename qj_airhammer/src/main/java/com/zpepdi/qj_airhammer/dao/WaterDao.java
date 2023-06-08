package com.zpepdi.qj_airhammer.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WaterDao {



    void doInsert(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);
    void doUpdate(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);


    Map<String,Object> doSelect(@Param("userId") Integer userId, @Param("kind") String kind);
    Map<String,Object> show(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);


    Map<String,Object> downinfo(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);
}
