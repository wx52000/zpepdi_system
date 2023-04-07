package com.zpepdi.qj_heating.service.Impl;

import com.zpepdi.qj_heating.dao.ThicknessDao;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.ThicknessSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ThicknessServiceImpl implements ThicknessSerice {

    @Autowired
    private ThicknessDao thicknessDao;


    @Override
    public Result queryRank() {
        return Result.ok(thicknessDao.queryRank());
    }

    @Override
    public Result queryjiezhi() {
        return Result.ok(thicknessDao.queryjiezhi());
    }
    @Override
    public Result queryyingli(Map<String, String> map) {
        String name = map.get("name");
        String product = name.split(":")[0];
        String rank = name.split(":")[1];
        Integer wendu = Integer.parseInt(map.get("wendu"));
        if(wendu>=250 && wendu<=660){
            wendu = (wendu/10)*10;
        }else if (wendu >= 200){
            wendu = 200;
        }else if (wendu >= 20){
            wendu = 20;
        }else {
            return null;
        }
        return Result.ok(thicknessDao.queryyingli(wendu,product,rank));
    }
}
