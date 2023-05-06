package com.zpepdi.audit_service.dao;

import com.zpepdi.audit_service.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface AdditionalConditionDao {

    int confirmDay();

    int declareDay();

    int declareDelay();

    Map<String,Object> queryDepWorkday(@Param("user") User user, @Param("map") Map<String,Object> map);
}
