package com.zpepdi.audit_service.service;

import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuditorInformationService {

    Result addAuditor(Map<String,Object> map);

    Result updateAuditor(@Param("user") User user, @Param("map") Map<String, Object> map);

    Result queryBySelf(User user,Map<String,Object> map);

    Result queryBySelfDown(User user,Map<String,Object> map);
}
