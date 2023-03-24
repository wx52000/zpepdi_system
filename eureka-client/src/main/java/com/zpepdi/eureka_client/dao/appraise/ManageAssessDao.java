package com.zpepdi.eureka_client.dao.appraise;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ManageAssessDao {

    void setManageAssessUser();

    void grantAuthority(Map<String,Object> map);

    void setUserAssess(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    void setUserAssessConfirm(@Param("userId") Integer userId);

    void setUserAssessTo0(@Param("userId") Integer userId, @Param("date") String date);

    Map<String,Object> queryNotAssessCount(@Param("userId") Integer userId, @Param("date") String date);

    void setUserAssessAvg(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    Map<String,Object> queryAssessSum(@Param("userId") Integer userId, @Param("date") String date);


}
