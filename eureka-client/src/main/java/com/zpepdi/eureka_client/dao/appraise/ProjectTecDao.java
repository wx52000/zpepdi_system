package com.zpepdi.eureka_client.dao.appraise;

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
}
