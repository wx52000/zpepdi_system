package com.zpepdi.eureka_client.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import com.zpepdi.eureka_client.entity.ExcelData;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.UserOut;
import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface UserService {


    Result queryById(Integer id);

    Result queryByUserIdSameDep(Integer id, String name);

    void add(User user);

    void addExcel(List<ExcelData> list);

    void del(Integer id);

    void upd(Map<String,Object> user);

    void pwdReset(Integer id);

    Result information(Integer id,String date);

    Result workdayLogById(Integer id, String date);

    Result queryByProject(Integer id);

    Result workdayLog(Map<String,Object> map);

    List<Map> query(User user);

    Result workday(String date);

    Result projectRole();

    Result manageProjectRole(Integer id);

    Result projectGeneral(Integer id);

    Result projectPrincipal(Integer id);

    Result volumeDesigner(Integer id);

    Result volumeChecker(Integer id);

    Result volumeHeadman(Integer id);

    Result workdayByManager(Integer id, String date);

    Result workdayByGeneral( String date,  Integer userId,
                               Integer id);

    Result logByGeneral(Integer id, Map<String,Object> map);

    Result workdayByPrincipal( String date,  Integer userId,
                                                 Integer id);

    Result logByPrincipal(Integer id, Map<String,Object> map);

    Result workdayByTec(Integer userId,
                               Map<String,Object> map);

    Result logByTec(Integer id, Map<String,Object> map);

    Result workdayByProject( String date,
                                               Integer id);

    //查询有权限打分的人员名单
    List<String> queryGrade();
    //根据工号排序
    List<Map > queryAll();

    Map queryToupd(Integer id);

    List<Map> queryByTid(Integer id);

    PageInfo<Map> queryNotSelf(User user);
    //用于被打分的数据查询
    Result queryToScore(Integer id, Map<String,Object> map);

    Result queryScoreList(User user);

    PageInfo<Map> queryNotScore(User user);

    Result queryAppriseAll();

    List<UserOut> queryAppraise(User user);
    //没有进行评价的人员名单
    List<UserOut> queryNotAppraise(User user);
    //没有被评价过的人员名单
    List<UserOut> queryNotScored(User user);
    //没有对专业级进行打分的人员名单
    List<UserOut> queryNotTecApp(User user);

    List<Map> queryByTec(Integer id);

    List<Map> queryByName(User user);

    List<Integer> queryByUsername(List<ExcelData> list);

    void paw(Integer id,User user);

    List<Map> queryPrincipal(Integer id);

    List<Map> userAll(Integer mode);

    List<Map> userAllAndState(Integer id);

    List<Map> userAllAndGroup(Integer id,Integer mode);

}
