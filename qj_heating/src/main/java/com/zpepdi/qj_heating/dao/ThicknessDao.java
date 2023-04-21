package com.zpepdi.qj_heating.dao;

import com.zpepdi.qj_heating.entity.Userpiping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ThicknessDao {
    //新增管道
    int upgdname(@Param("id") Integer id,@Param("gdname") String gdname);
    int savepiping(Userpiping userpiping);
    List<Userpiping> querypiping(@Param("username") String username,@Param("name") String name,@Param("defstr1") String defstr1);
    Userpiping byidquerypiping(@Param("id") Integer id);
    int delgd(@Param("id") Integer id);
    List<Map<String,String>> queryRank();
    List<Map<String,String>> queryjiezhi();
    String queryyingli(@Param("wendu") Integer wendu,@Param("product") String product,@Param("rank") String rank);
    List<Map<String,String>> queryyingliY();
}
