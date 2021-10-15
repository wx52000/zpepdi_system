package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Volume;
import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface VolumeService {

    void addFormPro(Project project);

    Map queryById(Integer id);

    List<Map<String, String>> queryByProjectId(Project project);

    List<Map<String,String>> queryByDate(Map map);

    void upd(Volume volume);

    void add(Volume volume);

    List<Map> queryVolume(String user,String volume);

    Result queryByNumber(Volume volume);

    List<Map<String, String>> personalVolume(Map<String,String> map);

    Result setReason(Map<String,String> map);

    Result setWorkdayHigh(Integer userId,Map<String,String> map);

    Result setWorkday(Integer userId,Map<String,String> map);

    Result queryVolumeWorkday(Map<String,String> map);
}
