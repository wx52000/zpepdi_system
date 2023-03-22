package com.zpepdi.qj_heating.dao;

import com.zpepdi.qj_heating.entity.TableList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//@Repository
@Mapper
public interface TabletestDao {
    int createTable( @Param("tableList")TableList tableList);
    int insertTable( @Param("tableList")TableList tableList);
//    int createTable( String tablename);
}
