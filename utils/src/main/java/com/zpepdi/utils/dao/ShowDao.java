package com.zpepdi.utils.dao;


import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
@Repository
public interface ShowDao {
    Map<String,Object> show(@Param("userId") Integer userId, @Param("nodeId") String nodeId, @Param("type") String type);

    Map<String,Object> temporary(@Param("userId") Integer userId, @Param("type") String type);
    List<Map<String,Object>> choice(@Param("userId") Integer userId, @Param("type") String type);

    Map<String,Object> tree(@Param("userId") Integer userId, @Param("type") String type);

    List<Map<String,Object>> folder(@Param("userId") Integer userId, @Param("type") String type, @Param("parentid") String parentid);

    Map<String,Object> folderlist( @Param("type") String type, @Param("parentid") String parentid, @Param("userId") Integer userId);
    void copyInfo( @Param("type") String type, @Param("nodeId") String nodeId, @Param("userId") Integer userId, @Param("parentid") String parentid, @Param("nowid") String nowid);

    List<Map<String,Object>> fileinfo( @Param("beforeid") String beforeid,@Param("userId") Integer userId, @Param("type") String type);

    void copyfile(@Param("type") String type, @Param("list") List<Map<String,Object>> list,@Param("parentid") String parentid,@Param("beforeid") String beforeid, @Param("userId") Integer userId);

}
