package com.zpepdi.audit_service.service.impl;

import com.zpepdi.audit_service.dao.AutoAssociationDao;
import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;
import com.zpepdi.audit_service.service.AutoAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoAssociationServiceImpl implements AutoAssociationService {

    @Autowired
    private AutoAssociationDao autoAssociationDao;

    @Override
    public Result queryBySelf(User user) {
        return Result.ok(autoAssociationDao.queryBySelf(user));
    }
}
