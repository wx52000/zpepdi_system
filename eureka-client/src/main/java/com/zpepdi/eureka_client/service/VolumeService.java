package com.zpepdi.eureka_client.service;

import com.alibaba.fastjson.JSONArray;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Volume;
import com.zpepdi.eureka_client.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface VolumeService {

    void addFormPro(Project project);

    Map queryById(Integer id);

    List<Map<String, String>> queryByProjectId(Integer userId,Project project);

    List<Map<String, String>> queryByProjectId(Project project);

    List<Map<String,String>> queryByDate(String date);

    void upd(Volume volume);

    void add(Volume volume);

    List<Map> queryVolume(String user,String volume);

    Result queryByNumber(Volume volume);

    List<Map<String, String>> personalVolume(Map<String,String> map);

    Result setReason(Map<String,String> map);

    Result setWorkdayHigh(Integer userId,Map<String,Object> map);

    Result setWorkday(Integer userId,Map<String,Object> map);


    Result setWorkdayAdvance(Integer userId,Map<String,Object> map);

    Result queryVolumeWorkday(Map<String,Object> map);

    Result queryVolumeWorkdayHigh(Map<String,Object> map);

    Result queryVolumeWorkdayLog(Map<String,String> map);

    Result queryBackupWorkdayLog(Map<String,String> map);

    Result setWorkdayState(String date, Integer old , Integer now);

    Result queryPlannedPublic(String date);

    Result tecProgress(Map<String,Object> map);

    Result manageTecProgress(Integer userId,Map<String,Object> map);

    Result tecVolumeCompleteByDate(Map<String,Object> map);

    Result tecVolumeInCompleteByDate(Map<String,Object> map);

    Result tecVolumePlanCompleteByDate(Map<String,Object> map);

    Result tecVolumeRecordByDate(Map<String,Object> map);

    Result tecProgressByProjectId(Integer userId, Map<String,Object> map);

    Result tecVolumeCompleteByDateByProjectId(Map<String,Object> map);

    Result tecVolumeInCompleteByDateByProjectId(Map<String,Object> map);

    Result tecVolumePlanCompleteByDateByProjectId(Map<String,Object> map);

    Result tecVolumeRecordByDateByProjectId(Map<String,Object> map);

    Result setPlanDate(MultipartFile file);

    Result setPlanDate(List<Map<String,Object>> list);

    List<Map<String,Object>> queryNotHave(String s);

    Result queryTodayEntryPlan();

    Result queryPlannedPublicDate(String search);

    Result setSinglePlanDate(Map<String,Object> map);

    Result queryRecently10Day(Integer userId);

    Result setPlanConfirm(Integer userId, Map<String,Object> map);

    Result updatePlanedPublicDate();


}
