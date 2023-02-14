package com.zpepdi.qj_airhammer.service;

import com.zpepdi.qj_airhammer.result.Result;

import java.util.Map;

public interface PtService {
    Result pt(Map<String, Object> map);

    Result ph(Map<String, Object> map);

    Result ps(Map<String, Object> map);

    Result hs(Map<String, Object> map);

    Result jl(Map<String, Object> map);

    Result ds(Map<String, Object> map);
}
