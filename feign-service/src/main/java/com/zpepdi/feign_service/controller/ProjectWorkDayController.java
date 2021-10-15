package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProjectWorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("projectWorkDay")
public class ProjectWorkDayController {
  private ProjectWorkDayService proWorkDayService;
  @Autowired
  public void setWorkDayService(@Qualifier("com.zpepdi.feign_service.service.ProjectWorkDayService") ProjectWorkDayService proWorkDayService){
    this.proWorkDayService = proWorkDayService;
  }

  @RequestMapping("drawLine")
  public Result drawLine(@RequestHeader Integer id){
    return proWorkDayService.drawLine(id);
  }

  @RequestMapping("queryUsedTecWorkDay")
  public Result queryUsedTecWorkDay(@RequestHeader Integer id) {
    return proWorkDayService.queryUsedTecWorkDay(id);
  }
  @RequestMapping("queryWorkDay")
  public Result queryWorkDay(@RequestHeader Integer id){
    return proWorkDayService.queryWorkDay(id);
  }

  @RequestMapping("queryProjectWorkDay")
  public Result queryProjectWorkDay(@RequestHeader Integer id){
    return proWorkDayService.queryProjectWorkDay(id);
  }

  @RequestMapping("queryMajorWorkDay")
  public Result queryMajorWorkDay(@RequestHeader Integer id){
    return proWorkDayService.queryMajorWorkDay(id);
  }

  @RequestMapping("queryBackupWorkDay")
  public Result queryBackupWorkDay(@RequestBody Map<String,String> map){
    return proWorkDayService.queryBackupWorkDay(map);
  }

  @RequestMapping("queryTecWorkDay")
  public Result queryTecWorkDay(@RequestHeader Integer id){
    return proWorkDayService.queryTecWorkDay(id);
  }

  @RequestMapping("queryTecVolumeRatio")
  public Result queryTecVolumeRatio(@UserId Integer id){
    return proWorkDayService.queryTecVolumeRatio(id);
  }

  @RequestMapping("setTecVolumeRatio")
  public Result setTecVolumeRatio(@RequestBody List<Map<String,Object>> list){
    return proWorkDayService.setTecVolumeRatio(list);
  }

  @RequestMapping("queryReserveWorkday")
  public Result queryReserveWorkDay(@RequestHeader Integer id){
    return proWorkDayService.queryReserveWorkDay(id);
  }

  @RequestMapping("setProWorkDay")
  public Result setProWorkDay(@RequestBody Map map){
    return proWorkDayService.setProWorkDay(map);
  }

  @RequestMapping("setTecWorkDay")
  public Result setTecWorkDay(@RequestBody Map map){
    return proWorkDayService.setTecWorkDay(map);
  }

  @RequestMapping("setBackupWorkDay")
  public Result setBackupWorkDay(@RequestBody Map map){
    return proWorkDayService.setBackupWorkDay(map);
  }

  @RequestMapping("setUserWorkDay")
  public Result setUserWorkDay(@RequestBody Map map){
    return proWorkDayService.setUserWorkDay(map);
  }


}
