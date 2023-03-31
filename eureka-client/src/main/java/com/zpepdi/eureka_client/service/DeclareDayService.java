package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

public interface DeclareDayService {

    Integer declareDay();

    Integer declareDelay();

    Result setDeclareDay(Integer day);

    Result setDeclareDelay(Integer day);
}
