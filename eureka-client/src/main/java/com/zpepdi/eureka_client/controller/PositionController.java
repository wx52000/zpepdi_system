package com.zpepdi.eureka_client.controller;


import com.zpepdi.eureka_client.annotation.RoleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.Position;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.PositionService;

import java.util.List;
import java.util.Map;

/*
职位管理，包含了权限管理
 */
@RestController
@RequestMapping("position")
public class PositionController {
  private PositionService positionService;
  @Autowired
  private void setPositionService(PositionService positionService){
    this.positionService = positionService;
  }

  @RequestMapping("queryByWeight")
  public Result queryByWeight(@RequestHeader Integer id){
    return Result.ok(positionService.queryByWeight(id));
  }

  @RequestMapping("query")
  public Result query(){
    return positionService.query();
  }

  @RequestMapping("newRole")
  public Result newRole(@RequestHeader String roleName){
    return positionService.newRole(roleName);
  }

  @RequestMapping("delRole")
  public Result delRole(@RequestHeader Integer id){
    return positionService.delRole(id);
  }


  @RequestMapping("update")
  public Result update(@RequestBody List<Position> position){
    return positionService.update(position);
  }

  //查询所有角色及权限
  @RequestMapping("queryRoleMenus")
  public Result queryRoleMenus(){
    return positionService.queryRoleMenus();
  }

  @RequestMapping("queryByRoleId")
  public Result queryByRoleId(@RoleId Integer id){
    return positionService.queryByRoleId(id);
  }

  //用于权限修改
  @RequestMapping("queryRole")
  public Result queryRole(@RequestHeader Integer id){
    return positionService.queryRole(id);
  }

  @RequestMapping("setRoleMenus")
  public Result setRoleMenus(@RequestBody Map<String,Object> map){
    return positionService.setRoleMenus(map);
  }
}
