package com.zpepdi.eureka_client.dao.appraise;

import com.zpepdi.eureka_client.tools.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ScientificSystemDao {

    double querySumWorkday(String date);

    int queryScientificStaffCount();

    void setWorkday(Map<String,Object> map);

    List<Map<String,Object>> queryDownDepartment();

    //未错 编译器问题
    List<List<Map<String,Object>>> querySumWorkdayByDep(@Param("id") Integer id,@Param("date")String date);

    List<Map<String,Object>> queryProduce(String date);

    boolean queryIsCreate(String date);
}
