package com.zpepdi.qj_heating.service;

import com.zpepdi.qj_heating.result.Result;

import java.util.Map;

public interface ThicknessSerice {

    Result queryRank();

    Result queryjiezhi();

    Result queryyingli(Map<String, String> map);
    Result queryyingliY();
}
