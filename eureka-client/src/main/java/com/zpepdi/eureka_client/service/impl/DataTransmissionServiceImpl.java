package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.dao.appraise.DataTransmissionDao;
import com.zpepdi.eureka_client.dao.zjepdi.ZJEPDIDataTransmissionDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.DataTransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataTransmissionServiceImpl implements DataTransmissionService {
    @Autowired
    private DataTransmissionDao dataTransmissionDao;

    @Autowired
    private ZJEPDIDataTransmissionDao transmissionDao;

    @Override
    @Transactional
    @Async("taskExecutor")
    public void dataTransmissionService() {
        new Thread(() -> {
            dataTransmissionDao.setAlive();
            List<Map<String,Object>> projectList = dataTransmissionDao.queryProjectNumber();
            if (projectList != null && projectList.size() > 0){
                projectList.forEach(item ->{
                    List<Map<String,Object>> list =
                            transmissionDao.queryFromZJEPDI(item.get("number").toString());
                    if (list != null && list.size()>0) {
                        dataTransmissionDao.insertData(item.get("id").toString(), list);
                    }
                });
                dataTransmissionDao.delNotAlive();
            }
            dataTransmissionDao.reSetUser();
        }).start();
        Result.ok();
    }

    @Override
    @Transactional
    @Async("taskExecutor")
    public void queryIncomeInformation(){
        List<Map<String,Object>> list = transmissionDao.queryIncomeInformation();
        dataTransmissionDao.setIncomeState();
        if (list.size() > 0) {
            dataTransmissionDao.insertIncome(list);
        }
        dataTransmissionDao.delIncome();
        Result.ok();
    }
}
