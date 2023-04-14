package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.ContractDao;
import com.zpepdi.eureka_client.dao.appraise.ProjectRelativeContractDao;
import com.zpepdi.eureka_client.dao.zjepdi.ZJEPDIDataTransmissionDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ProjectRelativeContractDao projectRelativeContractDao;
    @Autowired
    private ZJEPDIDataTransmissionDao zjepdiDataTransmissionDao;

    @Override
    public Result queryContractListBySearch(String search) {
        return Result.ok(zjepdiDataTransmissionDao.queryContractListBySearch(search));
    }

    @Override
    public Result insertContract(Integer userId, Map<String, Object> map) {
        if (map.get("number") != null && !map.get("number").toString().equals("")) {
            contractDao.insertContract(userId, map);
            projectRelativeContractDao.addProjectRelativeContract(userId, map);
        }
        return Result.ok(map);
    }
}
