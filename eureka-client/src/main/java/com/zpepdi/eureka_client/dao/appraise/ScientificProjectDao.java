package com.zpepdi.eureka_client.dao.appraise;

import com.zpepdi.eureka_client.entity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScientificProjectDao {

    void setProject(@Param("id") Integer id, @Param("map")Map<String,Object> map);

    void updateProject(@Param("id") Integer id, @Param("map")Map<String,Object> map);

    List<Map<String,Object>> queryByAdmin();
}
