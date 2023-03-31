package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScientificTaskService {

    Result querySurplus(Integer userId,Integer id);

    Result addTask(Integer userId,Map<String,Object> map);

    Result queryTask(Integer userId,Integer id);

    Result getCheckerList(Integer id);
    Result queryTaskBySubmitDate(Integer userId,Map<String,Object> map);

    Result taskSubmit(Map<String,Object> map);
}
