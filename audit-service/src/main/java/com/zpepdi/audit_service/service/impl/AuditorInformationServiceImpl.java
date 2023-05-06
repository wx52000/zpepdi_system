package com.zpepdi.audit_service.service.impl;

import com.zpepdi.audit_service.dao.AuditInformationDao;
import com.zpepdi.audit_service.dao.AuditorInformationDao;
import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;
import com.zpepdi.audit_service.service.AuditorInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AuditorInformationServiceImpl implements AuditorInformationService {
    @Autowired
    private AuditorInformationDao auditorInformationDao;

    @Override
    @Transactional
    public Result addAuditor(Map<String, Object> map) {
        auditorInformationDao.addAuditor(map);
        return Result.ok();
    }

    @Override
    public Result updateAuditor(User user, Map<String, Object> map) {
        auditorInformationDao.updateAuditor(user,map);
        return Result.ok();
    }

    @Override
    public Result queryBySelf(User user,Map<String,Object> map) {
        return Result.ok(auditorInformationDao.queryBySelf(user.getId(),map));
    }

    @Override
    public Result queryBySelfDown(User user, Map<String, Object> map) {
        return Result.ok(auditorInformationDao.queryBySelfDown(user.getId(), map));
    }
}
