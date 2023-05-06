package com.zpepdi.audit_service.service;

import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;

import java.util.Map;

public interface CcService {

    Result addCc(User user, Map<String,Object> map);

    Result queryBySelf(User user);

    Result queryById(Integer id);

    Result delCc(Integer id);
}
