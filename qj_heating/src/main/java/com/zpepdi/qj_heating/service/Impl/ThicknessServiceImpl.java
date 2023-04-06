package com.zpepdi.qj_heating.service.Impl;

import com.zpepdi.qj_heating.dao.ThicknessDao;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.ThicknessSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
