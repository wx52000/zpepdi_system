package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

import java.util.Map;

public interface ContractService {

    Result queryContractListBySearch(String search);

    Result insertContract(Integer userId, Map<String,Object> map);
}
