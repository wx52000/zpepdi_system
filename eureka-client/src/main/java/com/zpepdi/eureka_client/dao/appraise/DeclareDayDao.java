package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;

@Repository
public interface DeclareDayDao {

    Integer declareDay();

    Integer declareDelay();

    void setDeclareDay(Integer day);

    void setDeclareDelay(Integer id);
}
