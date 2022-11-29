package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

import java.util.Map;

public interface UserInformationService {

    Result query();

    Result queryById(Integer id);

    Result queryByManage(Integer id);

    Result insert(Map<String,Object> map);
}
