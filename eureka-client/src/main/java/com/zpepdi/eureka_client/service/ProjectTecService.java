package com.zpepdi.eureka_client.service;


import java.util.List;
import java.util.Map;

public interface ProjectTecService {

    List<Map> query(Integer id);

    List<String> queryById(Integer id);
}
