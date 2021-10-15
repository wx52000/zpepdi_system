package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> query();

    List<Department> queryNotUser();

    void add(Department department);

    Integer addString(String s);

    void del(Integer id);

    Integer queryName(String name);
}
