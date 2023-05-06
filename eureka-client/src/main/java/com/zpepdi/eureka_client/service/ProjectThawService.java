package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

import java.util.Map;

public interface ProjectThawService {

    Result addProjectThaw(Integer userId, Map<String,Object> map);
}
