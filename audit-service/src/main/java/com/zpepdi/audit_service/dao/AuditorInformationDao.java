package com.zpepdi.audit_service.dao;

import com.zpepdi.audit_service.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AuditorInformationDao {

//    添加审核人
    void addAuditor(Map<String,Object> map);

    void renew(Map<String,Object> map);

//    修改状态
    void updateAuditor(@Param("user") User user, @Param("map") Map<String, Object> map);

//    查询已审核
    List<Map<String,Object>> queryBySelf(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryBySelfDown(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    
}
