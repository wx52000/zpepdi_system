package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.ProjectExcelTec;
import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface ProjectWorkdayService {

  Result drawLine(Integer id);

  Result queryUsedTecWorkday(Integer id);

  Result queryProjectWorkday(Integer id);

  Result queryLimitAndAddSum(Integer id);

  Result queryProjectSumWorkday(Integer id);

  Result queryProjectTecWorkdayLog(Map<String,Object> map);

  Result queryWorkdayByGeneral(Integer userId, Map<String,Object> map);

  Result setWorkdayByGeneral(Integer id, Map<String,Object> map);

  Result setProWorkdayTotal(Integer id,Map map);

  Result setPrincipal(Integer id, Map<String,Object> map);


  Result delTec(Integer id, Map<String,Object> map);

  Result setProWorkday(Integer id,Map map);

  Result setNewProWorkday(Integer id,Map map);

  Result queryNewWorkdayByAddId(Integer id);

  Result queryNewWorkday(Integer id);

  Result delTecWorkday(Integer id,Map map);


  //Excel 整体比例 所有项目
  List<ProjectExcelTec> statisticAll(String date);

  List<Map<String,Object>> userAll(String date);

  List<Map<String,Object>> userByManage(String date,Integer userId);

  List<Map<String,Object>> userByPrincipal(String date,Integer userId, Integer id);

  List<Map<String,Object>> userByProject(String date,Integer userId, Integer id);

  //Excel 整体比例
  List<Map<String,String>> statistic(Integer id);

  List<Map<String,String>> everyoneAll(String date);

  List<Map<String,String>> everyone(Map map);

  List<Map<String,Object>> personal(Integer id);
}
