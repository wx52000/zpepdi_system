package com.zpepdi.audit_service.dao;

import com.zpepdi.audit_service.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdditionalDataDao {

    Map<String,Object> queryType1(Integer projectId);

    List<Map<String,Object>> queryType4(Map<String,Object> map);

    List<Map<String,Object>> queryType6(Map<String,Object> map);

    List<Map<String,Object>> queryType9(Map<String, Object> map);
    List<Map<String,Object>> queryType10(Map<String, Object> map);

    List<Map<String,Object>> queryType12(Map<String,Object> map);

    List<Map<String,Object>> queryType13(Map<String,Object> map);



}
