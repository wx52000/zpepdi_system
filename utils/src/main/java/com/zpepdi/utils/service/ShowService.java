package com.zpepdi.utils.service;

import com.zpepdi.utils.result.Result;

import java.util.Map;

public interface ShowService {
    Result show(Integer userId, Map<String, Object> map);

    Result temporary(Integer userId, Map<String, Object> map);

    Result choice(Integer userId, Map<String, Object> map);

    Result tree(Integer userId, Map<String, Object> map);
    Result folder(Integer userId, Map<String, Object> map);

    Result folderlist(Integer userId, Map<String, Object> map);

    Result copyInfo(Integer userId,Map<String, Object> map);

    Result copyFile(Integer userId,Map<String, Object> map);

}
