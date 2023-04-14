package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectRelativeContractDao {


    List<Map<String,Object>> queryByProjectId(Integer id);

    List<Map<String,Object>> queryNotSubmit(Integer userId);

    void addProjectRelativeContract(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    void queryRelativeLog(Map<String,Object> map);

    void delRelative(Map<String,Object> map);

    void submitRelative(Map<String,Object> map);

}
