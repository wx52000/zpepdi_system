package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Technology;

import java.util.List;
import java.util.Map;

public interface TechnologyService {

    List<Technology> queryAll();

    List<Technology> query(Integer id);

    List<Technology> queryByUserId(Integer id);

    Technology querySelf(Integer id);

    List<Technology> queryNotUser();

    List<Map> evaluate(Integer id);

    Integer queryByName(String name);

    void add(Technology technology);

    Integer addString(String name,Integer d);

    void del(Integer id);

    List<Technology> queryToSelected();

    List<Map> queryById(Project project);

    List<Map<String,Object>> queryByPrincipal(Integer userId, Integer id);
}
