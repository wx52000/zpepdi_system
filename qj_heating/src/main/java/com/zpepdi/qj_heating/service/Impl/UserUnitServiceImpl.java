package com.zpepdi.qj_heating.service.Impl;


import com.zpepdi.qj_heating.dao.UserUnitDao;
import com.zpepdi.qj_heating.entity.UserUnitys;
import com.zpepdi.qj_heating.entity.UserUnitcy;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.UserUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUnitServiceImpl implements UserUnitService {

    @Autowired
    private UserUnitDao userUnitDao;


    @Override
    public Result queryUserUnitys(String username) {
        return Result.ok(userUnitDao.queryUserys(username));
    }
    @Override
    public Result adduserys(UserUnitys userUnitys) {
        return Result.ok(userUnitDao.adduserys(userUnitys));
    }
    @Override
    public Result deluserys(String id) {
        return Result.ok(userUnitDao.deluserys(id));
    }
    @Override
    public Result upuserys(UserUnitys userUnitys) {
        return Result.ok(userUnitDao.upuserys(userUnitys));
    }


    @Override
    public Result queryUserUnitcy(String username) {
        return Result.ok(userUnitDao.queryUsercy(username));
    }
    @Override
    public Result addusercy(UserUnitcy userUnitcy) {
        return Result.ok(userUnitDao.addusercy(userUnitcy));
    }
    @Override
    public Result upusercy(UserUnitcy userUnitcy) {
        return Result.ok(userUnitDao.upusercy(userUnitcy));
    }

}
