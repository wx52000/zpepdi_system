package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.dao.appraise.DepartmentDao;
import com.zpepdi.eureka_client.dao.appraise.UserDao;
import com.zpepdi.eureka_client.entity.Department;
import com.zpepdi.eureka_client.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;
    private UserDao userDao;
    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao){
        this.departmentDao = departmentDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
      this.userDao = userDao;
    }
    @Override
    public List<Department> query() {
        return departmentDao.query();
    }

  @Override
  public List<Department> queryNotUser() {
      return departmentDao.queryNotUser();
  }

    @Override
    public Result queryByProjectAll(Integer userId, Integer id) {
        return Result.ok(departmentDao.queryByProjectAll(id));
    }

    @Override
    public Result queryByProjectId(Integer userId, Integer id) {
        return Result.ok(departmentDao.queryByProject(userId,id));
    }

    @Override
    public void add(Department department) {
        departmentDao.add(department);
    }

  @Override
  public Integer addString(String s) {
    Department department = new Department(s);
    departmentDao.add(department);
    return department.getId();
  }

  @Override
    public void del(Integer id) {
        departmentDao.del(id);
    }

    @Override
    public Integer queryName(String name) {
        return departmentDao.queryName(name);
    }

    @Override
    public Result queryCheckerAndDep() {
        return Result.ok(departmentDao.queryCheckerAndDep());
    }

    @Override
    public Result queryManageSurplus(Integer id) {
        return Result.ok(departmentDao.queryManageSurplus(id));
    }
}
