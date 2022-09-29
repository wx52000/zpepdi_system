package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.Department;
import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Department> query();

    List<Department> queryNotUser();

    Result queryByProjectAll(Integer userId,Integer id);

    Result queryByProjectId(Integer userId,Integer id);

    void add(Department department);

    Integer addString(String s);

    void del(Integer id);

    Integer queryName(String name);

    Result queryCheckerAndDep();

    Result queryManageSurplus(Integer id);
}
