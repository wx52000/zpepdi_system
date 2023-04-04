package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface ProjectRelativeContractService {

    Result addProjectRelativeContract(Integer userId, Map<String,Object> map);
}
