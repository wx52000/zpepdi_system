package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

import java.util.Map;

public interface SummaryProjectService {

    Result setSummaryProject(Integer userId, Map<String,Object> map);


    Result query(Integer userId);

    Result queryByHandler(Integer id);

    Result queryById(Integer id);

    Result queryRoleById(Integer id);
}
