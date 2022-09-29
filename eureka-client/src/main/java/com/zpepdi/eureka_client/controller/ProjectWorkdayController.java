package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectWorkdayService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("projectWorkday")
public class ProjectWorkdayController {
  private ProjectWorkdayService proWorkdayService;
  @Autowired
  public void setWorkdayService(ProjectWorkdayService proWorkdayService){
    this.proWorkdayService = proWorkdayService;
  }

//  @RequestMapping("drawLine")
//  public Result drawLine(@RequestHeader Integer id){
//    return proWorkdayService.drawLine(id);
//  }

  @RequestMapping("drawLine")
  public Result queryUsedTecWorkday(@RequestHeader Integer id) {
    return proWorkdayService.queryUsedTecWorkday(id);
  }

  @RequestMapping("queryProjectWorkday")
  public Result queryProjectWorkday(@RequestHeader Integer id){
    return proWorkdayService.queryProjectWorkday(id);
  }

  @RequestMapping("queryProjectSumWorkday")
  public Result queryProjectSumWorkday(@RequestHeader Integer id){
    return proWorkdayService.queryProjectSumWorkday(id);
  }

  @RequestMapping("queryProjectTecWorkdayLog")
  public Result queryProjectTecWorkdayLog(@RequestBody Map<String,Object> map){
    return proWorkdayService.queryProjectTecWorkdayLog(map);
  }

  @RequestMapping("setPrincipal")
  public Result setPrincipal(@UserId Integer id,@RequestBody Map<String,Object> map){
    return proWorkdayService.setPrincipal(id,map);
  }

  @RequestMapping("delTec")
  public Result delTec(@UserId Integer id,@RequestBody Map<String,Object> map){
    return proWorkdayService.delTec(id,map);
  }

  @RequestMapping("setProWorkdayTotal")
  public Result setProWorkdayTotal(@UserId Integer id,@RequestBody Map map){
    return proWorkdayService.setProWorkdayTotal(id,map);
  }

  @RequestMapping("setProWorkday")
  public Result setProWorkday(@UserId Integer id,@RequestBody Map map){
    return proWorkdayService.setProWorkday(id,map);
  }

  @RequestMapping("delTecWorkday")
  public Result delTecWorkday(@UserId Integer id,@RequestBody Map map){
    return proWorkdayService.delTecWorkday(id,map);
  }

  @RequestMapping("queryWorkdayByGeneral") 
  public Result queryWorkdayByGeneral(@UserId Integer userId, @RequestBody Map<String,Object> map){
    return proWorkdayService.queryWorkdayByGeneral(userId,map);
  }

  @RequestMapping("setWorkdayByGeneral")
  public Result setWorkdayByGeneral(@UserId Integer userId, @RequestBody Map<String,Object> map){
    return proWorkdayService.setWorkdayByGeneral(userId,map);
  }


  @RequestMapping("setNewProWorkday")
  public Result setNewProWorkday(@UserId Integer id,@RequestBody Map map){
    return proWorkdayService.setNewProWorkday(id,map);
  }


  @RequestMapping("queryNewWorkday")
  public Result queryNewWorkday(@UserId Integer userId,@RequestHeader("id")Integer id){
    return proWorkdayService.queryNewWorkday(id);
  }

  @RequestMapping("queryNewWorkdayByAddId")
  public Result queryNewWorkdayByAddId(@UserId Integer userId,@RequestHeader("id")Integer id){
    return proWorkdayService.queryNewWorkdayByAddId(id);
  }
}
