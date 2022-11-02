package com.zpepdi.eureka_client.service;


import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface ProjectTecService {

    List<Map> query(Integer id);

    List<String> queryById(Integer id);

    Result queryProjectByGeneral(Integer userId, Map<String,Object> map);

    Result insertProjectTec(Integer userId, Map<String,Object> map);
}
