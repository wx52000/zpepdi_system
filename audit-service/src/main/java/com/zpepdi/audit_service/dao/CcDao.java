package com.zpepdi.audit_service.dao;

import com.zpepdi.audit_service.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CcDao {

    void addCc(@Param("user") User user, @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryById(Integer id);

    List<Map<String,Object>> queryBySelf(User user);

    void delCc(Integer id);
}
