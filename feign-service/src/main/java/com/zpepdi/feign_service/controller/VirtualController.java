package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.entity.PrincipalWorkday;
import com.zpepdi.feign_service.entity.Virtual;
import com.zpepdi.feign_service.entity.VirtualDesigner;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.VirtualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("virtual")
public class VirtualController {
  private VirtualService virtualService;
  @Autowired
  public void setVirtualService(@Qualifier("com.zpepdi.feign_service.service.VirtualService") VirtualService virtualService){
    this.virtualService = virtualService;
  }

  @RequestMapping("setProject")
  public Result setProject(@RequestBody Virtual virtual){
    return virtualService.setProject(virtual);
  }

  @RequestMapping("query")
  public Result query(){
    return virtualService.query();
  }

  @RequestMapping("queryByUser")
  public Result queryByUser(@UserId Integer userId){
    return virtualService.queryByUser(userId);
  }

  @RequestMapping("queryById")
  public Result queryById(@RequestBody Map<String,Object> map){
    return virtualService.queryById(map);
  }

  @RequestMapping("queryUsedWorkday")
  public Result queryUsedWorkday(@RequestBody Map<String,Object> map,@UserId Integer userId){
    return virtualService.queryUsedWorkday(map,userId);
  }

  @RequestMapping("queryWorkday")
  public Result queryWorkday(@RequestBody Map<String,Object> map, @UserId Integer id){
    return virtualService.queryWorkday(map,id);
  }

  @RequestMapping("setWorkday")
  public Result setDesignerWorkday(@RequestBody Map<String,Object> map,@UserId Integer userId){
    return virtualService.setWorkday(map,userId);
  }

}
