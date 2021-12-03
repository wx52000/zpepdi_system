package com.zpepdi.eureka_client.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Volume;

import javax.security.sasl.SaslServer;
import java.util.List;
import java.util.Map;

@Repository
public interface VolumeDao {

    void addFormPro(Project project);

    void addExcelVolume(ExcelProject excelProject);

    Map queryById(Integer id);

    List<Map<String,String>> queryByProjectId(Map map);

    List<Map<String,String>> queryByDate(String date);

    void upd(Volume volume);

    void add(Volume volume);

    List<Map> queryVolume(@Param("user")String user,@Param("volume")String volume);

    List<Map> queryByNumber(Volume volume);

    List<Map<String,String>> personalVolume(Map map);

    void setReason(Map<String,String> map);

    void setWorkdayLog(@Param("userId") Integer userId,@Param("map") Map<String,String> map);

    void setWorkday(Map<String,String> map);

    void setWorkdayState(@Param("date") String date,@Param("old")Integer old, @Param("now")Integer now);

    Map<String,String> queryVolumeWorkday(Map<String,String> map);

    List<Map<String,String>> queryVolumeWorkdayLog(Map<String,String> map);

    List<Map<String,String>> queryBackupWorkdayLog(Map<String,String> map);

    Map<String,Object> queryUsableWorkday(Integer id);

    void setWorkdayHigh(Map<String,String> map);

    void setWorkdayGrant(Map<String, String> map);
}
