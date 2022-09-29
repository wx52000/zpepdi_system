package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.IncomeDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeDao incomeDao;

    @Override
    public Result queryIncome() {
        return Result.ok(incomeDao.queryIncome());
    }
}
