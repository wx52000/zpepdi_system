package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.result.Result;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ProjectService {

    void add(Project project);

    void addNumber(Project project);

    void upd(Project project);

    void updState(Integer id);

   void spider(Project project);

    List<Map> addExcel(ExcelProject excelProject) throws ParseException;

    Map queryById(Integer userId,Integer id);

    Map queryById(Integer id);

    Result queryHumanToBackup(Integer userId, Map<String,Object> map);

    Result distributeTecWorkday(Integer id , Map<String,Object> map);

    Result setManageByMajor(Integer id , Map<String,Object> map);

    Result setWorkdayBackup(Integer userId, List<Map<String,Object>> list, String date);
    //管理员查询
    List<Map> queryByAdmin(User user);
    //作为设总查询
    List<Map> queryByGeneral(User user);
    //作为主设人查询
    List<Map> queryByPrincipal(User user);
    List<Map> queryProByPrincipal(User user);
    //作为设计人查询
    List<Map> queryByDesigner(User user);
    //作为互校人查询
    List<Map> queryByChecker(User user);
    //作为组长查询
    Result queryByHeadman(User user);

    List<ExcelProject> queryExcel(Integer month);

    Result drawLine(Integer id);

    Result queryAll();

    Result queryPrincipal(Integer id);

    Result homepageVolume(Integer userId,Map<String,String> map);

    Result homepageProject(Integer userId);
}
