package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DataTransmissionDao {

    List<Map<String,Object>> queryProjectNumber();

    void setProject(@Param("id")Integer id,
                    @Param("map") Map<String,Object> map);

    void setAlive();

    void delNotAlive();

    void insertData(@Param("id")String id,
                    @Param("list") List<Map<String,Object>> list);

    void reSetUser();

    void setIncomeState();

    void insertIncome(List<Map<String,Object>> list);

    void delIncome();

    //判断是否存在为工时0卷册，若存在 则冻结专业不允许发工时
    void setFrozenExistNotWorkdayTec();
}
