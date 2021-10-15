package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.ProjectExcelTec;
import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface ProjectWorkDayService {

  Result drawLine(Integer id);

  Result queryUsedTecWorkDay(Integer id);
  //用于工时修改表单
  Result queryWorkDay(Integer id);

  Result queryProjectWorkDay(Integer id);

  Result queryMajorWorkDay(Integer id);

  Result queryBackupWorkDay(Map<String,String> map);


  Result queryTecVolumeRatio(Integer id);

  Result setTecVolumeRatio(List<Map<String,Object>> list);

  Result queryReserveWorkday(Integer id);

  Result setProWorkDay(Map map);

  Result setTecWorkDay(Map map);

  Result setBackupWorkDay(Map map);

  Result setUserWorkDay(Map map);

  //Excel 整体比例 所有项目
  List<ProjectExcelTec> statisticAll(String min , String max);


  //Excel 整体比例
  List<Map<String,String>> statistic(Integer id);

  List<Map<String,String>> everyoneAll(Map map);

  List<Map<String,String>> everyone(Map map);

  List<Map<String,String>> personal(Map<String,String> map);
}
