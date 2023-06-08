package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ContractDao {

    void insertContract(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);


}
