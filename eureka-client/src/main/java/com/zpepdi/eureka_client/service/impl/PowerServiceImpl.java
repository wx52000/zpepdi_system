package com.zpepdi.eureka_client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.appraise.PowerDao;
import com.zpepdi.eureka_client.entity.Power;
import com.zpepdi.eureka_client.service.PowerService;

import java.util.List;

@Service
public class PowerServiceImpl implements PowerService {
    private PowerDao powerDao;
    @Autowired
    public void setPowerDao(PowerDao powerDao){
        this.powerDao = powerDao;
    }
    @Override
    public List<Power> query() {
        return powerDao.query();
    }
}
