package com.zpepdi.qj_heating.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ThicknessDao {
    List<Map<String,String>> queryRank();
    List<Map<String,String>> queryjiezhi();
}
