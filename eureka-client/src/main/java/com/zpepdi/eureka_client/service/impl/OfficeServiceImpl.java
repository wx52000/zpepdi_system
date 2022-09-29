package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.OfficeDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeDao officeDao;

    @Override
    public Result query() {
        return Result.ok(officeDao.query());
    }

}
