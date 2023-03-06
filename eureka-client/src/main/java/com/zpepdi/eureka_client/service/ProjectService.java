package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.result.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ProjectService {

    Result queryUser(Integer userId, Integer id);

    Result setProject(Integer id, Project project);

    Result getOtherProjectN(Integer id, Map<String,Object> map);

    Result setOtherProject(Integer id, Map<String,Object> map);

    Result setMoney(Integer id, Map<String,Object> map);

    Result renewProject(Integer id, Map<String,Object> map);

    void addNumber(Project project);

    void renewError(Integer id);

    void updState(Integer id);

   void spider(Project project);

    Result sameName(Integer id);

    Result sameNameInsert(Map<String,Object> map);

    List<Map> addExcel(ExcelProject excelProject) throws ParseException;

    Map queryById(Integer userId,Integer id);

    Result queryChildren(Integer userId,Map<String,Object> map);

    Result queryTecById(Integer userId,Integer id);

    Result queryBySelfCheck(Integer userId);

    Result querySelfPbyId(Map<String,Object> map);

    Result queryDistribute(Integer userId, Map<String,Object> map);

    Result distributeTecWorkday(Integer id , Map<String,Object> map);

    Result queryUsedByTec(Integer id,Map<String,Object> map);

    Result queryUsableByTec(Integer id,Map<String,Object> map);


//    Result setWorkdayBackup(Integer userId, List<Map<String,Object>> list, String date);
    //管理员查询
    List<Map> queryByAdmin(Integer userId);

    List<Map> queryCompleteByAdmin(User user);
    //作为设总查询
    List<Map> queryByGeneral(User user);
    //作为主设人查询
    List<Map> queryByPrincipal(User user);
    List<Map> queryProByPrincipal(User user);
    //作为设计人查询
    List<Map> queryByDesigner(User user);
    //作为互校人查询
    List<Map> queryByChecker(User user);

    List<ExcelProject> queryExcel(Integer month);

    Result drawLine(Integer id);

    Result queryAll();

    Result queryPrincipal(Integer id);

    Result queryTaskByUser(Integer userId,Map<String,String> map);

    Result homepageProject(Integer userId);

    Result personalProject(Integer userId);

    Result setShowProject(Integer userId, Map<String,Object> map);

    Result getCheckerList( Integer type);

    Result getOtherProjectList();

    Result getCheckerListByProjectId( Integer id);

    Result setChecker(Integer userId,Map<String,Object> map);

    Result setTecChecker(Integer userId,Map<String,Object> map);

    Result queryNotDeclare(Integer userId, Map<String,Object> map);

    Result setDeclare(Integer userId, List<Map<String,Object>> list,String date);

    Object declareDay();

    Result setDeclareDay(Integer day);

    Object confirmDay();

    Result setConfirmDay(Integer day);

    Result declareLog(Integer userId, Map<String,Object> map);

    Result notSubmitByManage(Integer id);

    Result notSubmitByAdmin();

    Result backOff(Integer userId, Map<String,Object> map);

    Result projectProgress(Integer userId, Map<String,Object> map);

    Result projectProgressById(Integer userId, Map<String,Object> map);

    Result progressVolume(Map<String,Object> map);

    Result planVolume(Map<String,Object> map);

    Result progressIncompleteVolume(Map<String,Object> map);


    Result drawlineInfo(Integer userId,Map<String,Object> map);

    Result drawtotal(Map<String,Object> map);

    Result getdateInfo(Map<String,Object> map);

    HttpServletResponse downinfo(HttpServletResponse response,Integer userId, Map<String, Object> map);

}
