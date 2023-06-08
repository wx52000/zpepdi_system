package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Technology;

import java.util.List;
import java.util.Map;

@Repository
public interface TechnologyDao {

    List<Technology> queryAll();

    List<Technology> query(Integer id);

    List<Technology> queryByUserId(Integer id);

    Technology querySelf(Integer id);

    List<Technology> queryNotUser();

    List<Map> queryBydepNoU(Integer id);

    //评价页面
    List<Map> evaluate(Integer id);

    Integer queryByName(String name);

    void add(Technology technology);

    void del(Integer id);

    List<Technology> queryToSelected();

    List<Technology> queryByPro(Integer id);

    List<Map> getUser(Integer id);

    List<Map> queryById(Project project);

    List<Map<String,Object>> queryByPrincipal(@Param("userId")Integer userId, @Param("id")Integer id);

    List<String> querySpeciality(@Param("did") Integer did);

    List<String> querySpecialityByUserId(Integer userId);

    void delMajorTec(Integer manageUserId);

    Integer addMajorTec(Map<String,Object> map);
}
