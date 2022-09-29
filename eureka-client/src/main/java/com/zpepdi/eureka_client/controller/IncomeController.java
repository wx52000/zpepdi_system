package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @RequestMapping("query")
    public Result query(){
        return incomeService.queryIncome();
    }
}
