package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.ProjectExcelTec;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.ProjectWorkdayFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = ProjectWorkdayFallbackService.class)
public interface ProjectWorkDayService {

  @RequestMapping("projectWorkDay/drawLine")
  Result drawLine(@RequestHeader("id") Integer id);

  @RequestMapping("projectWorkDay/queryUsedTecWorkDay")
  Result queryUsedTecWorkDay(@RequestHeader("id") Integer id);

  @RequestMapping("projectWorkDay/queryWorkDay")
  Result queryWorkDay(@RequestHeader("id") Integer id);

  @RequestMapping("projectWorkDay/queryProjectWorkDay")
  Result queryProjectWorkDay(@RequestHeader("id") Integer id);

  @RequestMapping("projectWorkDay/queryMajorWorkDay")
  Result queryMajorWorkDay(@RequestHeader("id") Integer id);

  @RequestMapping("projectWorkDay/queryBackupWorkDay")
  Result queryBackupWorkDay(@RequestBody Map<String,String> map);

  @RequestMapping("projectWorkDay/queryTecWorkDay")
  Result queryTecWorkDay(@RequestHeader("id") Integer id);

  @RequestMapping("projectWorkDay/queryTecVolumeRatio")
  Result queryTecVolumeRatio(@RequestHeader("userId") Integer id);

  @RequestMapping("projectWorkDay/setTecVolumeRatio")
  Result setTecVolumeRatio(@RequestBody List<Map<String,Object>> list);

  @RequestMapping("projectWorkDay/queryReserveWorkday")
  Result queryReserveWorkDay(@RequestHeader("id") Integer id);

  @RequestMapping("projectWorkDay/setProWorkDay")
  Result setProWorkDay(@RequestBody Map map);

  @RequestMapping("projectWorkDay/setTecWorkDay")
  Result setTecWorkDay(@RequestBody Map map);

  @RequestMapping("projectWorkDay/setBackupWorkDay")
  Result setBackupWorkDay(@RequestBody Map map);

  @RequestMapping("projectWorkDay/setUserWorkDay")
  Result setUserWorkDay(@RequestBody Map map);

}
