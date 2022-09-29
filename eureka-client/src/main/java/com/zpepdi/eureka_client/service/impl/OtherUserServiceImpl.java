package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.OtherUserDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.OtherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service

public class OtherUserServiceImpl implements OtherUserService {
    @Autowired
    private OtherUserDao otherUserDao;

    @Override
    public Result queryById(Integer id) {
        return Result.ok(otherUserDao.queryById(id));
    }

    @Override
    public Result setPaw(Integer id, Map<String, Object> map) {
        otherUserDao.setPaw(id,map);
        return Result.ok();
    }

    @Override
    public Result queryByOffice(Integer id) {
        return Result.ok(otherUserDao.queryByOffice(id));
    }

    @Override
    public Result queryCheckList(Integer id) {
        return Result.ok(otherUserDao.queryCheckList(id));
    }

    @Override
    public Result setCheck(Map<String, Object> map) {
        otherUserDao.setCheck(map);
        return Result.ok();
    }
}
