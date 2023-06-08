package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Project;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectUserDao {

    void add(Project project);

    void addGeneral(Project project);

    void delGeneral(Project project);

    void addPrincipal(ExcelProject excelProject);

    List<Map> queryPrincipalById(Integer id);

    void del(Project project);
}
