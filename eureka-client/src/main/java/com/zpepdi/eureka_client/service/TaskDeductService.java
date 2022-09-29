package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


public interface TaskDeductService {

    Result add(Integer id, Map<String,Object> map);

    Result queryLog(Integer id);

    Result queryLogById(Integer id);
}
