package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NewsDao {

  Integer count(Integer id);

  Map<String,Object> queryCountByType(Integer id);

  //项目工时分配
  List<Map<String,Object>> queryByAdmin(Integer id);

  List<Map<String,Object>> queryByAdminList(Integer id);
  //项目额外工时申请
  List<Map<String,Object>> queryByAdmin1(Integer id);
//project_note 类型数据
  List<Map<String,Object>> queryByAdmin2(Integer id);
// project_children
  List<Map<String,Object>> queryByAdmin3(Integer id);

  List<Map<String,Object>> queryChildren(@Param("userId") Integer userId,@Param("projectId") Integer projectId);

  List<Map<String,Object>> queryByAdmin1List(Integer id);


  List<Map<String,Object>> queryByGeneral(Integer id);

  List<Map<String,Object>> queryByGeneral1(Integer id);

  List<Map<String,Object>> queryByGeneral2(Integer id);

  List<Map<String,Object>> queryByGeneralList(@Param("id") Integer id, @Param("userId")Integer userId,
                                              @Param("handlerId")Integer handlerId, @Param("submitDate")String submitDate);

  List<Map<String,Object>> queryByGeneral2List(@Param("id") Integer id, @Param("userId")Integer userId,
                                              @Param("handlerId")Integer handlerId, @Param("submitDate")String submitDate);
  //未审核的专业工时分配
  List<Map<String,Object>> queryByHeadman(Integer id);

  //卷册工时组长审核
  List<Map<String,Object>> queryByHeadman2(Integer id);

  List<Map<String,Object>> resultByGeneral(Integer id);

  List<Map<String,Object>> resultByGeneral1(Integer id);

//  List<Map<String,Object>> resultByPrincipal(Integer id);

  //工时分配回退
  List<Map<String,Object>> resultByPrincipal1(Integer id);

  List<Map<String,Object>> resultByPrincipal2(Integer id);


  List<Map<String,Object>> queryByHeadmanList(@Param("id")Integer id, @Param("handlerId")String handlerId,
                                              @Param("tec") String tec, @Param("check")Integer check);


  void check(@Param("list") List<Map<String,Object>> list,
             @Param("check") Integer check, @Param("id")Integer id);

  void setProjectWorkday(@Param("id") Integer id, @Param("num") Integer num);

  void setProjectBackup(@Param("id") Integer id, @Param("num") Integer num);

  void checkLog0(@Param("map") Map<String,Object> map,
             @Param("check") Integer check, @Param("id")Integer id);

  void checkLog0List(@Param("map") Map<String,Object> map,
                 @Param("check") Integer check, @Param("id")Integer id);

//  void checkLog2(@Param("list") List<Map<String,Object>> list,
//                 @Param("check") Integer check, @Param("id")Integer id);

  void checkLog3(@Param("map") Map<String,Object> map,
                 @Param("check") Integer check, @Param("id")Integer id);

  void checkLog4(@Param("map") Map<String,Object> map,
                 @Param("check") Integer check, @Param("id")Integer id);

  void checkLog4List(@Param("map") Map<String,Object> map,
                 @Param("check") Integer check, @Param("id")Integer id);


  /**
   * @param map
   * @param check
   * @param id
   */
  void checkLog5List(@Param("map") Map<String,Object> map,
                 @Param("check") Integer check, @Param("id")Integer id);

  void checkLog7(@Param("map") Map<String,Object> map,
          @Param("check") Integer check, @Param("id")Integer id);

  List<Map<String,Object>> queryLog(@Param("id")Integer id);

  List<Map<String,Object>> queryLogList(@Param("id") Integer id, @Param("type") Integer type);


  List<Map<String,Object>> scientificGeneral(Integer id);

  List<Map<String,Object>> scientificFiles(Integer id);

  List<Map<String,Object>> scientificHeadman(Integer id);

  List<Map<String,Object>> scientificTask(@Param("userId") Integer userId,
                                          @Param("projectId") Integer projectId,
                                          @Param("submitDate") String submitDate,
                                          @Param("creator") Integer creator);

  //查询个人审核的实现的记录
  List<Map<String,Object>> adminQueryApply(Integer userId);

  //个人审核项目的信息
  Map<String,Object> queryInformation7(Map<String,Object> map);

  //个人审核项目的信息
  List<Map<String,Object>> queryInformation8(Map<String,Object> map);

  //个人审核的项目分配信息
  Map<String,Object> queryInformation0(Map<String,Object> map);


  //个人审核的项目分配到各专业的信息
  Map<String,Object> queryInformationList0(Integer id);

  //个人审核的项目外审申请分配信息
  Map<String,Object> queryInformation1(Map<String,Object> map);
  //个人审核的项目外审申请分配到各专业信息
  Map<String,Object> queryInformationList1(Integer id);

  Map<String,Object> queryInformation3(Map<String,Object> map);

  List<Map<String,Object>> queryInformation4(Map<String,Object> map);

  List<Map<String,Object>> queryInformation5(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

  void withdrawOverAll0(Map<String,Object> map);

  void withdrawOverAll3(Map<String,Object> map);

  void withdrawOver5(Map<String,Object> map);

  String querySubmitDateNow();

  void withdrawOverAll7(Map<String,Object> map);

  void withdrawOverAll8(Map<String,Object> map);

  boolean isWithdrawOverAll0(Map<String,Object> map);

  boolean isWithdrawOverAll3(Map<String,Object> map);

  boolean isWithdrawOverAll7Or8(Map<String,Object> map);



}

