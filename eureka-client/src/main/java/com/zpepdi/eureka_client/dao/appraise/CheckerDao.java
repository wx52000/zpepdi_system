package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CheckerDao {

    void del(Map<String,Object> map);

    void set(Map<String,Object> map);

    Map<String,Object> queryChecker2();
    List<Map<String,Object>> queryDepChecker();

    void delDepChecker(Map<String,Object> map);

    void setDepChecker(Map<String,Object> map);

    void setCheckerAmount(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    void setCheckerUsed(Map<String,Object> map);

    Map<String,Object> queryByProjectAndTec(@Param("userId") Integer userId,
                                            @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryByUserId(Integer userId);

}
