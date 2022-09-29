package com.zpepdi.eureka_client.service;


import com.zpepdi.eureka_client.result.Result;

import java.util.Map;

public interface CheckerService {


    Result del(Map<String,Object> map);

    Result set(Map<String,Object> map);

    Result queryDepChecker();

    Result delDepChecker(Map<String,Object> map);

    Result setDepChecker(Map<String,Object> map);
}
