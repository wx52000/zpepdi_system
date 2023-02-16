package com.zpepdi.qj_heating.dao;


import com.zpepdi.qj_heating.entity.UserUnit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
//@Repository
public interface UserUnitDao {

    @Select("select * from user_unit_config where username = #{username}")
    UserUnit queryUserconfig(String username);

    @Insert("insert into user_unit_config(username , name , userconfig)\n" +
            "        values ( #{username} ,#{name} ,#{userconfig})")
    int adduserconfig(UserUnit userUnit);

    //注意要加script标签
    @Update("<script> " + "update user_unit_config" +
            "<set>" + "<if test=\"userconfig != null and userconfig !=''\">userconfig=#{userconfig},</if>" +
            "</set>" + "where username=#{username}" +
            " </script> ")
    int upuserconfig(UserUnit userUnit);
}
