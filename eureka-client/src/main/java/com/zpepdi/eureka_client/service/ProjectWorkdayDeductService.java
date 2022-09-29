package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectWorkdayDeductService {

    Result add(Integer id, Map<String,Object> map);

    Result close(Integer id);

    Result queryLog(Integer id);

    Result queryLogById(Integer id);

    Result queryLogByProject(Integer id);
}
