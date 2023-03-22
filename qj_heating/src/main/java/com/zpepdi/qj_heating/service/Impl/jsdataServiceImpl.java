package com.zpepdi.qj_heating.service.Impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zpepdi.qj_heating.dao.jsdataDao;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.jsdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DS("slave_1")
public class jsdataServiceImpl implements jsdataService {

    @Autowired
    private jsdataDao jsdatadao;

    @Override
    public Result queryTableNames() {
        return Result.ok(jsdatadao.queryTableNames());
    }
    @Override
    public Result queryzhcn() {
        return Result.ok(jsdatadao.queryzhcn());
    }

    @Override
    public Result queryTabledata(String tablename) {
        return Result.ok(jsdatadao.queryTabledata(tablename));
    }
    @Override
    public Result queryTablebz(String tablename) {
        return Result.ok(jsdatadao.queryTablebz(tablename));
    }
}
