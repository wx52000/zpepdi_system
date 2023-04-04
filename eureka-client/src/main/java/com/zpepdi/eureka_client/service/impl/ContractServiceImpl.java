package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ContractDao;
import com.zpepdi.eureka_client.dao.zjepdi.ZJEPDIDataTransmissionDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ZJEPDIDataTransmissionDao zjepdiDataTransmissionDao;

    @Override
    public Result queryContractListBySearch(String search) {
        return Result.ok(zjepdiDataTransmissionDao.queryContractListBySearch(search));
    }
}
