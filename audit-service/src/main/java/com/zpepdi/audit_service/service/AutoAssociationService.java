package com.zpepdi.audit_service.service;


import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;


public interface AutoAssociationService {

    Result queryBySelf(User user);
}
