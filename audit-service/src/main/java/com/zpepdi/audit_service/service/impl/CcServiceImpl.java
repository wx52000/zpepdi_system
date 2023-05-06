package com.zpepdi.audit_service.service.impl;

import com.zpepdi.audit_service.dao.CcDao;
import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;
import com.zpepdi.audit_service.service.CcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CcServiceImpl implements CcService {
    @Autowired
    private CcDao ccDao;


    @Override
    public Result addCc(User user, Map<String, Object> map) {
        ccDao.addCc(user,map);
        return Result.ok(map);
    }

    @Override
    public Result queryBySelf(User user) {
        return Result.ok(ccDao.queryBySelf(user));
    }

    @Override
    public Result queryById(Integer id) {
        return Result.ok(ccDao.queryById(id));
    }

    @Override
    public Result delCc(Integer id) {
        ccDao.delCc(id);
        return Result.ok();
    }
}
