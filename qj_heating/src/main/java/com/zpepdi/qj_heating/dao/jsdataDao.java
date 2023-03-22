package com.zpepdi.qj_heating.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface jsdataDao {
    List<Map<String,Object>> queryTableNames();
    List<Map<String,Object>> queryzhcn();

    List<Map<String,Object>> queryTabledata(@Param("tablename") String tablename);
    List<Map<String,Object>> queryTablebz(@Param("tablename") String tablename);
}
