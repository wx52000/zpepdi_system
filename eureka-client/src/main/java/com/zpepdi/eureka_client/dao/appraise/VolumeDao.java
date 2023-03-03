package com.zpepdi.eureka_client.dao.appraise;

import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Volume;

import java.util.List;
import java.util.Map;

@Repository
public interface VolumeDao {

    void addFormPro(Project project);

    void addExcelVolume(ExcelProject excelProject);

    void del(Integer id);

    Map queryById(Integer id);

    List<Map<String,String>> queryByProjectId(Integer id);

    List<Map<String,String>> queryByDate(String date);

    void upd(Volume volume);

    void add(Volume volume);

    List<Map> queryVolume(@Param("user")String user,@Param("volume")String volume);

    List<Map> queryByNumber(Volume volume);

    List<Map<String,String>> personalVolume(Map map);

    void setReason(Map<String,String> map);

    void setWorkdayLog(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    void setWorkday(@Param("id")Integer id, @Param("map") Map<String,Object> map);

    void setWorkdayState(@Param("date") String date,@Param("old")Integer old, @Param("now")Integer now);

    void setWorkdayStateById(Map<String,Object> map);

    Map<String,String> queryVolumeWorkdayAndRatio(Map<String,Object> map);

    Map<String,String> queryVolumeWorkdayHigh(Map<String,Object> map);

    List<Map<String,String>> queryVolumeWorkdayLog(Map<String,String> map);

    List<Map<String,String>> queryBackupWorkdayLog(Map<String,String> map);

    Map<String,Object> queryUsableWorkday(Integer id);

    void setWorkdayHigh(@Param("id")Integer id, @Param("map")Map<String,Object> map);

    void setWorkdayAdvance(@Param("id")Integer id,@Param("map") Map<String, Object> map);

    List<Map<String,Object>> queryPlannedPublic(@Param("start") String start,@Param("end") String end);

    List<Map<String,Object>> tecProgress(Map<String, Object> map);

    List<Map<String,Object>> manageTecProgress(Map<String, Object> map);

    List<Map<String,Object>> tecVolumeCompleteByDate(Map<String, Object> map);

    List<Map<String,Object>> tecVolumePlanCompleteByDate(Map<String,Object> map);

    List<Map<String,Object>> tecVolumeInCompleteByDate(Map<String, Object> map);

    List<Map<String,Object>> tecVolumeRecordByDate(Map<String,Object> map);

    List<Map<String,Object>> tecProgressByProjectId(Map<String, Object> map);

    List<Map<String,Object>> tecVolumeCompleteByDateByProjectId(Map<String, Object> map);

    List<Map<String,Object>> tecVolumePlanCompleteByDateByProjectId(Map<String,Object> map);

    List<Map<String,Object>> tecVolumeInCompleteByDateByProjectId(Map<String, Object> map);

    List<Map<String,Object>> tecVolumeRecordByDateByProjectId(Map<String,Object> map);

    void setPlanDate(List<Map<String,Object>> list);

    List<Map<String,Object>> queryNotHave(String s);

    List<Map<String,Object>> queryTodayEntryPlan();

    List<Map<String,Object>> queryPlannedPublicDate(String search);

    void setSinglePlanDate(@Param("userId")Integer userId,@Param("map") Map<String,Object> map);


    void setSingleRemark(Map<String,Object> map);


    List<Map<String,Object>> queryRecently10Day(Integer userId);

    void setPlanConfirm(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    void updatePlanedPublicDate(Map<String,Object> map);

    void setPlanRecord(Map<String,Object> map);

    void resetPlanDate(Map<String,Object> map);

    List<Map<String,Object>> queryConfirmTec(@Param("userId") Integer userId,@Param("id") Integer id);

    List<Map<String,Object>> queryConfirmNotCheck(Map<String,Object> map);

    boolean queryConfirmState(Map<String,Object> map);

    void timingConfirmWorkday(String date);

    void sendConfirm(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    void sendConfirmVolume(Map<String,Object> map);
}
