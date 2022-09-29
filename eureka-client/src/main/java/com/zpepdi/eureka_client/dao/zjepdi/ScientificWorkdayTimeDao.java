package com.zpepdi.eureka_client.dao.zjepdi;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScientificWorkdayTimeDao {

    Double queryDepartmentSum(Map<String,Object> map);

    List<Map<String,Object>> queryDepartment(Map<String,Object> map);
}
