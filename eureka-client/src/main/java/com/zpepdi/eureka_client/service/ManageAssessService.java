package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

import java.util.Map;

public interface ManageAssessService {

    Result queryDidAssessSum();

    Result setUserAssess(Integer userId, Map<String,Object> map);

    Result setAssessRemark(Integer userId, Map<String,Object> map);

    Result queryAssessSum(Integer userId);
}
