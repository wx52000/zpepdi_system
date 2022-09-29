package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScientificService {

    Result query();

    Result queryById(Integer userId, Integer id);

    Result addScientific(Integer userId,Map<String,Object> map);
}
