package com.zpepdi.qj_heating.service;


import com.zpepdi.qj_heating.entity.UserUnitys;
import com.zpepdi.qj_heating.entity.UserUnitcy;
import com.zpepdi.qj_heating.result.Result;

public interface UserUnitService {

    /**
     * 查询用户预设
     * @param username
     * @return
     */
    Result queryUserUnitys(String username);
    /**
     * 查询用户常用
     * @param username
     * @return
     */
    Result queryUserUnitcy(String username);

    /**
     * 添加用户预设
     * @param userUnitys
     * @return
     */
    Result adduserys(UserUnitys userUnitys);

    /**
     * 删除用户预设
     * @param id
     * @return
     */
    Result deluserys(String id);

    /**
     * 添加用户常用
     * @param userUnitcy
     * @return
     */
    Result addusercy(UserUnitcy userUnitcy);

    /**
     * 修改用户预设
     * @param userUnitys
     * @return
     */
    Result upuserys(UserUnitys userUnitys);

    /**
     * 修改用户常用
     * @param userUnitcy
     * @return
     */
    Result upusercy(UserUnitcy userUnitcy);
}
