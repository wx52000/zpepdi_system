package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ProjectExcelTec;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectWorkdayDao {


  Map queryProWorkday(Integer id);

  Boolean queryTecPrincipalExist(Integer id);

  List<Map> queryTecWorkdayAmount(Integer id);

  Map queryProSumWorkday(@Param("userId") Integer userId , @Param("id") Integer id);

  List<Map<String,Object>> queryProjectTecWorkdayLog(Map<String,Object> map);

  List<Map> queryTecSumWorkdayAmount(@Param("id") Integer id, @Param("role") Integer role,
                                     @Param("userId") Integer userId);

  //同上
  List<Map> queryTecWorkday(Integer id);
  //查询各专业已用工时
  List<Map> queryUsedTecWorkday(Integer id);

  Map<String,Object> queryWorkdayByGeneral(@Param("userId") Integer userId, @Param("id") Integer id);

  void  setMWorkdayByGeneral(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

  void  setBWorkdayByGeneral(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

  Double queryUsableManageWorkday(Integer id);

  Double queryUsedManageWorkdayByDate(@Param("id") Integer id, @Param("date") String date);

  Double queryUsableBackupWorkday(Integer id);
  List<Map> queryBackupWorkdayAmount(Map<String,String> map);
  List<Map> queryReserveWorkdayRatio(@Param("id") Integer id);

  //修改项目分配工时
  void setProWorkday(Map map);

  //判断是否可以新增此专业
  boolean IsInsert(Map<String,Object> map);

  void setTec(@Param("id") Integer id, @Param("map") Map<String,Object> map);

  void setPrincipal(@Param("id") Integer id, @Param("map") Map<String,Object> map);

  Integer queryWorkdayTecId(Map<String,Object> map);

  void delPrincipal(@Param("id")Integer id, @Param("map")Map<String,Object> map);

  Boolean isOperate(@Param("id")Integer id,@Param("map")Map<String,Object> map);

  void delTec(@Param("id")Integer id, @Param("map")Map<String,Object> map);

  void setNewProWorkday(@Param("id") Integer id, @Param("map") Map map);

  void setProWorkdayDistribut(@Param("id") Integer id, @Param("map") Map map);

  void setNewProWorkdayDistribut(@Param("id") Integer id, @Param("map") Map map);

  void setTecWorkday(@Param("userId") Integer userId, @Param("id")Integer id,
                     @Param("list") List<Map> list, @Param("check")Integer check);

  void setNewTecWorkday(@Param("userId") Integer userId, @Param("id")Integer id,
                     @Param("list") List<Map> list, @Param("check")Integer check, @Param("addId")Integer addId);


  void delTecWorkday(Map<String,Object> map);

  void setUserWorkday(@Param("id")Integer id,
                     @Param("list") List<Map> list,
                     @Param("type") Integer type);

  List<ProjectExcelTec> statisticAll(String date);

  List<Map<String,String>> statisticByDate(@Param("id")Integer id, @Param("date")String date);

  List<Map<String,String>> statistic(Integer id);

  List<Map<String,String>> everyoneAll(String date);

  List<Map<String,String>> everyone(Map map);


  List<Map<String,Object>> queryTecVolumeRatio(Integer id);

  void reSetFrozen(@Param("userId") Integer userId, @Param("projectId") Integer projectId);

  void setTecVolumeRatio(@Param("id")Integer id, @Param("map")Map<String,Object> map);

  void setVolumeWorkday(@Param("map")Map<String,Object> map,@Param("id")Integer id,@Param("date")String date);

  List<Map<String,Object>> queryNewWorkday(Integer id);

  Map<String,Object> queryNewWorkdayByAddId(Integer id);

  List<Map<String,Object>> queryTecWorkdayList(Integer id);

  Map<String,Object> queryLimitAndAddSum(Integer id);

  List<Map<String,Object>> queryBackupList(Integer id);

  List<Map<String,Object>> queryOfficeWorkday(Integer year);


}
