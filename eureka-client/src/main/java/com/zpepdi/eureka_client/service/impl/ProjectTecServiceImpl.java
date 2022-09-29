package com.zpepdi.eureka_client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.appraise.ProjectTecDao;
import com.zpepdi.eureka_client.service.ProjectTecService;

import java.util.List;
import java.util.Map;

@Service
public class ProjectTecServiceImpl implements ProjectTecService {
    private ProjectTecDao projectTecDao;
    @Autowired
    public void  setProjectTecDao(ProjectTecDao projectTecDao){
        this.projectTecDao = projectTecDao;
    }

    @Override
    public List<Map> query(Integer id) {
        return projectTecDao.query(id);
    }

  @Override
  public List<String> queryById(Integer id) {
    return projectTecDao.queryById(id);
  }
}
