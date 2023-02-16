package com.zpepdi.qj_heating.service;


import com.zpepdi.qj_heating.entity.UserUnit;
import com.zpepdi.qj_heating.result.Result;

public interface UserUnitService {
    Result queryUserUnitconfig(String username);
    Result adduserconfig(UserUnit userUnit);
    Result upuserconfig(UserUnit userUnit);
}
