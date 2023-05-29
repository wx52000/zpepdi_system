package com.zpepdi.utils.service;

import com.zpepdi.utils.result.Result;

import java.util.List;
import java.util.Map;

public interface SaveService {
    Result save(Integer userId, Map<String, Object> map);


    Result temporary(Integer userId, Map<String, Object> map);

    Result tree(Integer userId, Map<String, Object> map);


    Result folder(Integer userId, Map<String, Object> map);

    Result delete(Integer userId, List<Map<String,Object>> list);

    Result updatefolder(Integer userId, Map<String, Object> map);

}
