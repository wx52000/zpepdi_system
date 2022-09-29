package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.Department;

import java.util.List;
import java.util.Map;

@Repository
public interface DepartmentDao {

    List<Department> query();

    List<Department> queryNotUser();

    List<Map<String,Object>> queryByProjectAll(Integer id);

    List<Map<String,Object>> queryTecByProjectAll(@Param("userId") Integer userId,@Param("id") Integer id, @Param("name") Integer name);

    //queryByProject子查询
    List<Map<String,Object>> queryTecByProject(@Param("id") Integer id, @Param("name") Integer name,@Param("userId")Integer userId);

    List<Map<String,Object>> queryByProject(@Param("userId")Integer userId,@Param("id") Integer id);

    void add(Department department);

    void del(Integer id);

    Integer queryName(String name);

    Integer selectByTec(Integer id);

    List<Map<String,Object>> queryCheckerAndDep();

    Map<String,Object> queryManageSurplus(Integer id);

    Map<String,Object> querySurplus(@Param("userId")Integer userId, @Param("did")Integer did);
}
