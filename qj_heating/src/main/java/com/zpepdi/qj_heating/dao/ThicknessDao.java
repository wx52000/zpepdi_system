package com.zpepdi.qj_heating.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ThicknessDao {
    List<Map<String,String>> queryRank();
    List<Map<String,String>> queryjiezhi();
    String queryyingli(@Param("wendu") Integer wendu,@Param("product") String product,@Param("rank") String rank);
    List<Map<String,String>> queryyingliY();
}
