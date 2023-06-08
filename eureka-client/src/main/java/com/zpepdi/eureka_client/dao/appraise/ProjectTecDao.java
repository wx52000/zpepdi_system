package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Project;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectTecDao {

    void add(Project project);

    void addExcel(ExcelProject excelProject);

    List<Map> query(Integer id);

    List<String> queryById(Integer id);

    void del(Project project);

    List<Map<String,Object>> queryProjectByGeneral(@Param("userId") Integer userId,
                                                   @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryTecByProjectId(Integer id);

    //insertId 需导入项目的id , selectId 导入项目的id
    void insertProjectTec(@Param("userId")Integer userId,
                          @Param("map") Map<String,Object> map);
}
