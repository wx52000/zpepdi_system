package com.zpepdi.utils.dao;


import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
@Repository
public interface SaveDao {
    void doInsert(@Param("userId") Integer userId, @Param("datalist") String datalist, @Param("type") String type, @Param("nodeId") String nodeId);
    Map<String,Object> doSelect(@Param("userId") Integer userId,  @Param("datalist") String datalist, @Param("type") String type, @Param("nodeId") String nodeId);
    void doUpdate(@Param("userId") Integer userId, @Param("datalist") String datalist, @Param("type") String type, @Param("nodeId") String nodeId);

    Map<String,Object> selectTree(@Param("userId") Integer userId, @Param("type") String type);

    void insertTree(@Param("userId") Integer userId,  @Param("data") String data, @Param("type") String type, @Param("startid") String startid);

    void updateTree(@Param("userId") Integer userId,  @Param("data") String data, @Param("type") String type, @Param("startid") String startid);


    Map<String,Object> selectTemporary(@Param("userId") Integer userId, @Param("type") String type);

    void insertTemporary(@Param("userId") Integer userId,  @Param("data") String data, @Param("type") String type);

    void updateTemporary(@Param("userId") Integer userId,  @Param("data") String data, @Param("type") String type);


    void insertfolder(@Param("userId") Integer userId,  @Param("parentid") String parentid, @Param("name") String name, @Param("type") String type, @Param("restype") String restype, @Param("nodeid") String nodeid);

    void delete(@Param("userId") Integer userId, @Param("list") List<Map<String,Object>> list);
    void updatefolder(@Param("userId") Integer userId,  @Param("parentid") String parentid, @Param("name") String name, @Param("type") String type, @Param("restype") String restype, @Param("nodeid") String nodeid);


}
