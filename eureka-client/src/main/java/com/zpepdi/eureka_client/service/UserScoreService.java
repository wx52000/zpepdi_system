package com.zpepdi.eureka_client.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import com.zpepdi.eureka_client.entity.PartExcel;
import com.zpepdi.eureka_client.entity.PersonalExcel;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.UserScore;

import java.util.List;
import java.util.Map;

public interface UserScoreService {

    PageInfo<Map> queryByGradeId(User user);

    Map queryByScoreId(User user);

    void appraise(Integer id,UserScore userScore);

    List<Map> queryScore(User user);

    List<Map> query(User user);


    List<List<String>> detail(User user,List<String> userName);

    void add(List<UserScore> list);

    void del(UserScore userScore);

    List<PersonalExcel> excel1(User user);

    List<Map> selectByGradeId(User user);

    List<PartExcel>  part(Integer mode , List<Map> toData,Integer month);

    void backups();

    void delete();

}
