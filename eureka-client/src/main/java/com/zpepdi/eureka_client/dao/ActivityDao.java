package com.zpepdi.eureka_client.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.*;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityDao {

  void setProject(Activity activity);

  List<Map> query();

  List<Map> queryByUser(Integer id);

  Map queryById(Integer id);

  List<Map<String,Object>> queryByRole(@Param("id") Integer id,@Param("role") Integer role);

  void addUser(@Param("id")Integer id,@Param("list") List<Map<String,Object>> list);

  void setState(@Param("id")Integer id,@Param("list")List<Map<String,Object>> list,@Param("role") Integer role);

  Map<String,Object> queryUsedWorkday(@Param("map") Map<String,Object> map,@Param("id") Integer id);

  List<Map<String,Object>> queryWorkday(@Param("map") Map<String,Object> map, @Param("id") Integer id);

  void setWorkday(@Param("map") Map<String,Object> map,@Param("id") Integer id);
}
