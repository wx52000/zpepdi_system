package com.zpepdi.eureka_client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.appraise.TechnologyDao;
import com.zpepdi.eureka_client.dao.appraise.UserDao;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Technology;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.service.TechnologyService;

import java.util.List;
import java.util.Map;

@Service
public class TechnologyServiceImpl implements TechnologyService {
    private TechnologyDao technologyDao;
    private UserDao userDao;
    @Autowired
    public  void  setTechnologyDao(TechnologyDao technologyDao ){

        this.technologyDao = technologyDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
      this.userDao = userDao;
    }

    @Override
    public List<Technology> queryAll() {
        return technologyDao.queryAll();
    }

    @Override
    public List<Technology> query(Integer id) {
        return technologyDao.query(id);
    }

    @Override
    public List<Technology> queryByUserId(Integer id) {
        return technologyDao.queryByUserId(id);
    }

    @Override
    public Technology querySelf(Integer id) {
        return technologyDao.querySelf(id);
    }

    @Override
  public List<Technology> queryNotUser() {
    return technologyDao.queryNotUser();
  }

  @Override
  public List<Map> evaluate(Integer id) {
    User user1 = userDao.queryById(id);
    if (user1.getGrade() == 1) {
      return technologyDao.evaluate(id);
    }else
      return null;
  }


  @Override
    public Integer queryByName(String name) {
        return technologyDao.queryByName(name);
    }

    @Override
    public void add(Technology technology) {
        technologyDao.add(technology);
    }

  @Override
  public Integer addString(String name, Integer d) {
      Technology technology = new Technology();
      technology.setName(name);
      technology.setDid(d);
      technologyDao.add(technology);
    return technology.getId();
  }

  @Override
    public void del(Integer id) {
        technologyDao.del(id);
    }

    @Override
    public List<Technology> queryToSelected() {
        return technologyDao.queryToSelected();
    }

    @Override
    public List<Map> queryById(Project project) {
        return technologyDao.queryById(project);
    }

    @Override
    public List<Map<String,Object>> queryByPrincipal(Integer userId, Integer id) {
        return technologyDao.queryByPrincipal(userId,id);
    }
}
