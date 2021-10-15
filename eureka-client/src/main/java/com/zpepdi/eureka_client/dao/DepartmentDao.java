package com.zpepdi.eureka_client.dao;

import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.Department;

import java.util.List;

@Repository
public interface DepartmentDao {

    List<Department> query();

    List<Department> queryNotUser();

    void add(Department department);

    void del(Integer id);

    Integer queryName(String name);

    Integer selectByTec(Integer id);
}
