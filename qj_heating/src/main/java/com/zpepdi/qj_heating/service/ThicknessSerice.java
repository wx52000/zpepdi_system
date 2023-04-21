package com.zpepdi.qj_heating.service;

import com.zpepdi.qj_heating.entity.Userpiping;
import com.zpepdi.qj_heating.result.Result;

import java.util.Map;

public interface ThicknessSerice {

    Result upgdname(Integer id, String gdname);
    Result savepiping(Userpiping userpiping);
    Result querypiping(String username,String name,String defstr1);
    Result byidquerypiping(Integer id);
    Result delgd(Integer id);
    Result queryRank();

    Result queryjiezhi();

    Result queryyingli(Map<String, String> map);
    Result queryyingliY();
}
