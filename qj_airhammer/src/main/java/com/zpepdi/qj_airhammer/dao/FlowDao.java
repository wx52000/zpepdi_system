package com.zpepdi.qj_airhammer.dao;

import com.zpepdi.qj_airhammer.entity.Flow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface FlowDao {
    void doInsert(@Param("userId") Integer userId, @Param("personal") String personal, @Param("flow") List<Map<String,Object>> flow);
    Map<String,Object> selectTemporary(@Param("userId") Integer userId, @Param("type") String type);

    void insertTemporary(@Param("userId") Integer userId,  @Param("data") String data, @Param("type") String type);

    void updateTemporary(@Param("userId") Integer userId,  @Param("data") String data, @Param("type") String type);
}
