package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.UserInformationDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    private UserInformationDao userInformationDao;

    @Override
    public Result query() {
        return Result.ok(userInformationDao.query());
    }

    @Override
    public Result queryById(Integer id) {
        return Result.ok(userInformationDao.queryById(id));
    }

    @Override
    public Result queryByManage(Integer id) {
        return Result.ok(userInformationDao.queryByManage(id));
    }

    @Override
    public Result insert(Map<String,Object> map) {
        map.put("userId",map.get("id"));
        userInformationDao.insert(map);
        return Result.ok();
    }
}
