package com.zpepdi.qj_heating.dao;


import com.zpepdi.qj_heating.entity.UserUnitys;
import com.zpepdi.qj_heating.entity.UserUnitcy;
import org.apache.ibatis.annotations.*;

@Mapper
//@Repository
public interface UserUnitDao {

//    查询用户预设
    @Select("select * from user_unit_ys where username = #{username}")
    UserUnitys[] queryUserys(String username);

//    查询用户常用
    @Select("select * from user_unit_cy where username = #{username}")
    UserUnitcy[] queryUsercy(String username);

//    新增用户预设
    @Insert("insert into user_unit_ys(username , name , userys)\n" +
            "        values ( #{username} ,#{name} ,#{userys})")
    int adduserys(UserUnitys userUnitys);

//    删除用户预设
    @Delete("delete from user_unit_ys where id = #{id}")
    int deluserys(String id);

//    新增用户常用
    @Insert("insert into user_unit_cy(username , name , usercy)\n" +
            "        values ( #{username} ,#{name} ,#{usercy})")
    int addusercy(UserUnitcy userUnitcy);

//    更新用户预设(暂无此需求)
    @Update("<script> " + "update user_unit_ys" +
            "<set>" + "<if test=\"userys != null and userys !=''\">userys=#{userys},</if>" +
            "</set>" + "where username=#{username}" +
            " </script> ")
    int upuserys(UserUnitys userUnitys);

//    更新用户常用(自动更新)
    @Update("<script> " + "update user_unit_cy" +
            "<set>" + "<if test=\"usercy != null and usercy !=''\">usercy=#{usercy},</if>" +
            "</set>" + "where username=#{username}" +
            " </script> ")
    int upusercy(UserUnitcy userUnitcy);
}
