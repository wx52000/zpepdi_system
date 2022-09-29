package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface OtherUserService {

    Result queryById(Integer id);

    Result setPaw(Integer id, Map<String,Object> map);

    Result queryByOffice(Integer id);

    Result queryCheckList(Integer id);

    Result setCheck(Map<String,Object> map);
}
