package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CheckerDao {

    void del(Map<String,Object> map);

    void set(Map<String,Object> map);

    List<Map<String,Object>> queryDepChecker();

    void delDepChecker(Map<String,Object> map);

    void setDepChecker(Map<String,Object> map);

    void setCheckerAmount(Map<String,Object> map);
}
