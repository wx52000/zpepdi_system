package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IncomeDao {

    List<Map<String,Object>> queryIncome();
}
