package com.zpepdi.eureka_client.service;


import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface DataTransmissionService {

    void dataTransmissionService();

    void queryIncomeInformation();

    Result queryCheckerList(String processInstanceId);
}
