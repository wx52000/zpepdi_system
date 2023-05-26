package com.zpepdi.audit_service.dao;

import com.zpepdi.audit_service.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AuditInformationDao {

    int queryCount(User user);
    //    添加审核信息
    void addAuditInformation(@Param("user") User user, @Param("map") Map<String, Object> map);

    void renew(@Param("map") Map<String, Object> map);

    Map<String,Object> queryByKey(@Param("user") User user, @Param("map") Map<String,Object> map);

    //    查询我发起的审核数据
    List<Map<String,Object>> queryBySelf(@Param("user") User user, @Param("map") Map<String,Object> map);

    Map<String,Object> queryById(Integer id);

    void updateState(Map<String,Object> map);

    void updateData(@Param("id") Integer id, @Param("data") String data);
    //    未审核时选择回退
    void delAuditInformation(Integer id);

    void setIsSeen(Integer id);
}
