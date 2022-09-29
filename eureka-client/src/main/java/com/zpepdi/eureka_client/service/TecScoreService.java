package com.zpepdi.eureka_client.service;

import org.apache.ibatis.annotations.Param;
import com.zpepdi.eureka_client.entity.TecPartExcel;
import com.zpepdi.eureka_client.entity.TecScore;
import com.zpepdi.eureka_client.entity.TechnologyExcel;
import com.zpepdi.eureka_client.entity.User;

import java.util.List;
import java.util.Map;

public interface TecScoreService {


    List<Map> queryByGradeId(Integer id);

    Map queryByScoreId(User user);

    void appraise(Integer id,  TecScore tecScore);

    List<Map> queryScore(User user);

    List<Map> query(User user);

    List<List<String>> detail(User user,List<String> userName);

    List<TechnologyExcel> excel(User user);

    List<TecPartExcel> part(List<Map> toData,Integer month);

    void backups();

    void delete();
}
