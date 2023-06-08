package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.GradeScore;
import com.zpepdi.eureka_client.entity.UserScore;

import java.util.List;
import java.util.Map;

@Repository
public interface GradeScoreDao {

    void add(GradeScore list);

    void del(GradeScore gradeScore);

    void addExcel(@Param("grade") List<Integer> grade , @Param("score")List<Integer> score);

    void amongExcel(List<Integer> list);

    void updState(List<UserScore> id);

    void resetState();

    void delAll();
    //用于查询该评价人与哪些专业的被评价人关联
    List<Map> queryTec(Integer id);

    ///用于查询该评价人与哪些部门的被评价人关联
    List<Map> queryDep(Integer id);
}
