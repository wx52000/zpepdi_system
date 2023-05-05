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
    int upfilename(@Param("id") Integer id,@Param("defstr3") String defstr3);
    int upsort(@Param("id") Integer id,@Param("defstr2") Integer defstr2);
    int updefstr4(@Param("id") Integer id,@Param("defstr4") String defstr4);
    int savepiping(Userpiping userpiping);
    int upgd(Userpiping userpiping);
    List<Userpiping> querypiping(@Param("username") String username,@Param("name") String name,@Param("defstr1") String defstr1);
    List<Userpiping> byfilequerypiping(@Param("username") String username,@Param("name") String name,@Param("defstr3") String defstr3);
    String querymaxsort(Userpiping userpiping);
    List<String> queryfilenamelist(Userpiping userpiping);
    Userpiping byidquerypiping(@Param("id") Integer id);
    int delgd(@Param("id") Integer id);
    List<Map<String,String>> queryRank();
    List<Map<String,String>> queryjiezhi();
    String queryyingli(@Param("wendu") Integer wendu,@Param("product") String product,@Param("rank") String rank);
    List<Map<String,String>> queryyingliY();

    List<Map<String,String>>  querycailiao3087();

    List<Map<String,String>>  querycailiao5310();
}
