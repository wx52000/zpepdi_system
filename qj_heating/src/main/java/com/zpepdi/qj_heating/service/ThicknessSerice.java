package com.zpepdi.qj_heating.service;

import com.zpepdi.qj_heating.entity.Userpiping;
import com.zpepdi.qj_heating.result.Result;

import java.util.List;
import java.util.Map;

public interface ThicknessSerice {

    Result upgdname(Integer id, String gdname);
    Result upsort(Integer id, Integer defstr2);
    Result savepiping(Userpiping userpiping);
    Result filesave(Userpiping userpiping);
    Result filesave2(Userpiping userpiping);
    Result dakaifile(Userpiping userpiping);
    Result queryfilenamelist(Userpiping userpiping);
    Result upgd(Userpiping userpiping);
    List<Userpiping> querypiping(Userpiping userpiping);
    Result byidquerypiping(Integer id);
    Result delgd(Integer id);
    Result delfenzu(Userpiping userpiping);
    Result queryRank();

    Result queryjiezhi();

    Result queryyingli(Map<String, String> map);
    Result queryyingliY();

    Result querycailiao3087();

    Result querycailiao5310();

    Result rename2(Userpiping userpiping);

    Result upfenzusort(Userpiping userpiping);
}
