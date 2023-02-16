package com.zpepdi.qj_heating.service.Impl;


import com.zpepdi.qj_heating.dao.UserUnitDao;
import com.zpepdi.qj_heating.entity.UserUnit;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.UserUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUnitServiceImpl implements UserUnitService {

    @Autowired
    private UserUnitDao userUnitDao;
//    @Autowired
//    public void setUserUnitDao(UserUnitDao userUnitDao){
//        this.userUnitDao = userUnitDao;
//    }


    @Override
    public Result queryUserUnitconfig(String username) {
        return Result.ok(userUnitDao.queryUserconfig(username));
    }
    @Override
    public Result adduserconfig(UserUnit userUnit) {
        return Result.ok(userUnitDao.adduserconfig(userUnit));
    }
    @Override
    public Result upuserconfig(UserUnit userUnit) {
        return Result.ok(userUnitDao.upuserconfig(userUnit));
    }

}
