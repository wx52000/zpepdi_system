package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.Power;

import java.util.List;

@Repository
public interface PowerDao {

    List<Power> query();
}
