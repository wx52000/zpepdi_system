package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserInformationDao {

    List<Map<String,Object>> query();

    Map<String,Object> queryById(Integer id);

    List<Map<String,Object>> queryByManage(Integer id);

    void insert(Map<String,Object> map);


}
