package com.zpepdi.eureka_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.PrincipalWorkday;
import com.zpepdi.eureka_client.entity.Virtual;
import com.zpepdi.eureka_client.entity.VirtualDesigner;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.VirtualService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("virtual")
public class VirtualController {
  private VirtualService virtualService;
  @Autowired
  public void setVirtualService(VirtualService virtualService){
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
  public Result queryByUser(@RequestHeader Integer userId){
    return virtualService.queryByUser(userId);
  }

  @RequestMapping("queryById")
  public Result queryById(@RequestBody Map<String,Object> map){
    return virtualService.queryById(map);
  }

  @RequestMapping("queryUsedWorkday")
  public Result queryUsedWorkday(@RequestBody Map<String,Object> map,@RequestHeader("userId") Integer userId){
    return virtualService.queryUsedWorkday(map,userId);
  }

  @RequestMapping("queryWorkday")
  public Result queryWorkday(@RequestBody Map<String,Object> map, @RequestHeader("userId") Integer id){
    return virtualService.queryWorkday(map,id);
  }

  @RequestMapping("setWorkday")
  public Result setDesignerWorkday(@RequestBody Map<String,Object> map,@RequestHeader Integer userId){
    return virtualService.setWorkday(map,userId);
  }
}
