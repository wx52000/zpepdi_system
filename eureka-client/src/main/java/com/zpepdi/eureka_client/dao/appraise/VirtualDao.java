package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.Virtual;

import java.util.List;
import java.util.Map;

@Repository
public interface VirtualDao {

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  void setProject(Virtual virtual);

  List<Map> query();

  List<Map> queryByUser(Integer id);

   Map<String,Object> queryById(Integer id);

  void addUser(@Param("id")Integer id,@Param("list") List<Map<String,Object>> list);

  void addUserWorkday(@Param("id")Integer id,@Param("list") List<Map<String,Object>> list, @Param("date")String date);

  void setState(@Param("id")Integer id, @Param("list")List<Map<String,Object>> list,@Param("role")Integer role);

  List<Map<String,String>> queryRole(@Param("map")Map map,@Param("role") Integer role);

  Map<String,Object> queryUsedWorkday(@Param("map") Map<String,Object> map,@Param("id") Integer id);

  List<Map<String,Object>> queryWorkday(@Param("map") Map<String,Object> map, @Param("id") Integer id);

  void setWorkday(@Param("map")Map<String,Object> map, @Param("id") Integer id);


}
