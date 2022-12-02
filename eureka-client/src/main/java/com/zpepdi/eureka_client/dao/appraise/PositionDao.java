package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.Position;

import java.util.List;
import java.util.Map;

@Repository
public interface PositionDao {
  List<Map> query();

  void newRole(String name);

  //menusId查数据库
  boolean permissionByMenus(@Param("userId") Integer userId, @Param("menusId")Integer menusId);


  void delRole(Integer id);

  List<Map> queryByWeight(Integer id);

  void  update(List<Position> list);

  List<Map> queryRoleMenus();

  void setMenus(Map<String,Object> map);

  void setButton(Map<String,Object> map);
  
  void delMenus(Map<String,Object> map);

  void delButton(Map<String,Object> map);

  List<Map<String,Object>> queryTreeByRoleId(Integer id);

  List<Map<String,Object>> queryMenusByRoleId(@Param("roleId")Integer roleId, @Param("menusId")Integer menusId);

  List<String> queryButtonByRoleId(Integer id);


  List<String> queryRole(Integer id);

  List<String> queryRoleButton(@Param("roleId")Integer roleId);
}
