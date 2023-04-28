package com.zpepdi.audit_service.service;

import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;

import java.util.Map;

public interface AuditInformationService {

    Result queryCount(User user);
    Result addAuditInformation(User user, Map<String,Object> map);

    Result renew(User user, Map<String,Object> map);
    Result queryBySelf(User user, Map<String,Object> map);

    Result submitAdult(User user, Map<String,Object> map);

    Result queryById(Integer id);

    Result delAuditInformation(Integer id);
}
