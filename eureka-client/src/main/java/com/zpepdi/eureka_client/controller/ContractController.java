package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @RequestMapping("queryListBySearch")
    public Result queryContractListBySearch(@RequestHeader("search")String search){
        return contractService.queryContractListBySearch(search);
    }

    @RequestMapping("insertContract")
    public Result insertContract(@UserId Integer userId, Map<String,Object> map){
        return contractService.insertContract(userId,map);
    }

}
