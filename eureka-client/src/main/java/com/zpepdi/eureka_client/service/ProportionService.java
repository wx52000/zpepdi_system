package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.Proportion;

import java.util.List;
import java.util.Map;

public interface ProportionService {

    void add(Proportion proportion);

    Proportion queryLastTime(Proportion proportion);

    List<Map> queryLastTwoTimes(Proportion proportion);
}
