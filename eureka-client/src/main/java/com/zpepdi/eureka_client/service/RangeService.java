package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.Range;

import java.util.Map;

public interface RangeService {


    Range query();

    void update(Range range);

    Map queryDate();

    void updateDate(Range range);
}
