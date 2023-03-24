package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ManageAssessService {

    Result queryDidAssessSum();
    Result grantAuthority(Map<String,Object> map);
    Result setUserAssess(Integer userId, Map<String,Object> map);

    Result setAssessRemark(Integer userId, Map<String,Object> map);

    Result setUserAssessConfirm(Integer userId);

    Result setUserAssessTo0(Integer userId);

    Result setUserAssessAvg(Integer userId);

    Result queryAssessSum(Integer userId);
}
