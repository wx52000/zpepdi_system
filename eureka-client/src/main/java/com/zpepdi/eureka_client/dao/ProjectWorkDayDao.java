package com.zpepdi.eureka_client.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ProjectExcelTec;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectWorkDayDao {

  //图表
  //同上 && 用于修改
  Map queryProWorkDay(Integer id);
  //同上
  List<Map> queryTecWorkDay(Integer id);
  //查询各专业已用工时
  List<Map> queryUsedTecWorkDay(Integer id);

  //查询分配的工时，用于修改
  List<Map> queryTecWorkDayAmount(Integer id);

  Double queryUsableManageWorkday(Integer id);

  Double queryUsedManageWorkdayByDate(@Param("id") Integer id, @Param("date") String date);

  Double queryUsableBackupWorkday(Integer id);
  List<Map> queryBackupWorkDayAmount(Map<String,String> map);
  List<Map> queryReserveWorkDayRatio(@Param("id") Integer id);
  //修改项目分配工时
  void setProWorkDay(Map map);

  void setProWorkDayDistribut(Map map);

  void setManageWorkday(@Param("id") Integer id, @Param("map")Map<String,Object> map);

  void setTecWorkDay(@Param("id")Integer id,
                     @Param("list") List<Map> list);
  void setBackupWorkDay(@Param("id")Integer id,
                     @Param("list") List<Map> list,@Param("date")String date);
  void setUserWorkDay(@Param("id")Integer id,
                     @Param("list") List<Map> list,
                     @Param("type") Integer type);

  List<ProjectExcelTec> statisticAll(String date);

  List<Map<String,String>> statisticByDate(@Param("id")Integer id, @Param("date")String date);

  List<Map<String,String>> statistic(Integer id);

  List<Map<String,String>> everyoneAll(String date);

  List<Map<String,String>> everyone(Map map);

  List<Map<String,String>> workdayByProject(List<String> list);


  List<Map<String,Object>> queryTecVolumeRatio(Integer id);

  void setTecVolumeRatio(List<Map<String,Object>> list);
}
