package com.zpepdi.eureka_client.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectDao {

    void add(Project project);

    void addNumber(Project project);

    void addExcel(ExcelProject excelProject);

    void upd(Project project);

    void updState(Integer id);

    void spider(Project project);

    Map queryById(Integer id);

    List<Map<String,Object>> queryHumanToBackup(@Param("userId")Integer id, @Param("map")Map<String,Object> map);

    Map<String,Object> queryUsed(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    Map<String,Object> queryUsedManage(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    String queryAmountByMajor(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    void distributeTecWorkday(Map map);

    void setWorkdayBackup(@Param("userId") Integer userId,
                          @Param("list") List<Map<String, Object>> list, @Param("date") String date);

    void setWorkdayBackupLog(@Param("userId") Integer userId,
                             @Param("list") List<Map<String, Object>> list, @Param("date") String date);
    //管理员查询
    List<Map> queryByAdmin(User user);
    List<Map> queryProByAdmin(User user);
    List<Map> queryVolBYAdmin(User user);
//作为设总查询
    List<Map> queryByGeneral(User user);
//作为主设人查询
    List<Map> queryByPrincipal(User user);
    List<Map> queryProByPrincipal(User user);
    List<Map> queryDesigner(User user);
//作为设计人查询
    List<Map> queryByDesigner(User user);
//作为互校人查询
    List<Map> queryByChecker(User user);
//作为组长查询
    List<Map> queryByHeadman(User user);

    List<Map> queryExcel(User user);

    List<Map> drawLine(Integer id);

    //用于虚拟项目关联实体
    List<Map> queryAll();

    List<Map> queryPrincipal(Integer id);

    List<Map> homepageVolume(Map map);

    List<Map> homepageProject(String name);
}
