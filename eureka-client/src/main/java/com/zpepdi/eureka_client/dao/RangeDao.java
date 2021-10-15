package com.zpepdi.eureka_client.dao;

import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.Range;

import java.util.Map;

@Repository
public interface RangeDao {

    Range query();

    void update(Range range);

    Map queryDate();

    void updateDate(Range range);
}
