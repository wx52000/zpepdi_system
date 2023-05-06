package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScientificLeaderDao {

    void setLeader(@Param("userId")Integer userId,
                   @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryLeader(Integer id);

    void setLeaderChecker(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    void delLeader(Integer id);

    Double querySurplus(Integer id);

    //工时审核人新增任务发放
    void  addTermByGeneral(@Param("userId")Integer userId,
                  @Param("map") Map<String,Object> map);

    //负责人新增项申请工时
    void  addTermByLeader(@Param("userId")Integer userId,
                           @Param("map") Map<String,Object> map);

    void setToCheck(Integer id);

    void  addScientificTermFile(@Param("userId") Integer userId,
                                @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryTerm(@Param("userId") Integer userId, @Param("projectId")Integer projectId);

    Map<String,Object> queryTermById(Integer id);

    List<Map<String,Object>> queryFilesByTerm(Integer id);

    Map<String,Object> queryFilesById(Integer id);
}
