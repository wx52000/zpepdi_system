package com.zpepdi.eureka_client.dao.appraise;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ManageAssessDao {

    void setManageAssessUser();

    void setUserAssess(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    Map<String,Object> queryAssessSum(@Param("userId") Integer userId, @Param("date") String date);


}
