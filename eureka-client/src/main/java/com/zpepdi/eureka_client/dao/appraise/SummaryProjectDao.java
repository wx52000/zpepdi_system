package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SummaryProjectDao {

    void addBasic(@Param("userId")Integer userId,@Param("map") Map<String,Object> map);

    void addStaff(@Param("projectId") Integer projectId,
            @Param("list") List<Object> list,@Param("role") Integer role);

    void delStaff(@Param("projectId") Integer projectId,@Param("role") Integer role);

    void addInformation(@Param("userId")Integer userId,@Param("map") Map<String,Object> map);

    //查询项目的对接人和跟踪人
    List<Map<String,Object>> queryRoleById(Integer id);

    List<Map<String,Object>> queryByHandler(Integer id);

    List<Map<String,Object>> query(Integer id);

    List<Map<String,Object>> queryById(Integer id);


}
