package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface ProjectRelativeContractService {

    Result queryByProjectId(Integer id);

    Result queryNotSubmitByUserId(Integer userId);

    Result addProjectRelativeContract(Integer userId, Map<String,Object> map);

    Result queryRelativeLog(Map<String,Object> map);

    Result delRelative(Map<String,Object> map);

    Result submitRelative(Map<String,Object> map);
}
