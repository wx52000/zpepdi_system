package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OtherUserDao {

  Map<String,Object> queryById(Integer id);

  void setPaw(@Param("id") Integer id, @Param("map") Map<String,Object> map);

  List<Map<String,Object>> queryByOffice(Integer id);

  List<Map<String,Object>> queryCheckList(Integer id);

  void setCheck(Map<String,Object> list);

}
