package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectDao {

    Map<String,Object> queryUser(@Param("userId")Integer userId, @Param("id")Integer id);
    // 查询角色在项目中的权限 返回1则管理员 2设总 3主设人 null则无权限
    Integer queryProjectRole(@Param("userId") Integer userId,
                             @Param("projectId") Integer projectId);

    boolean queryByNameAndNum(Map<String,Object> map);

    void setProject(@Param("id") Integer id,@Param("project") Project project);

    void setOtherProject(@Param("id") Integer id,@Param("map")Map<String,Object> map, @Param("type")Integer type);

    int valWorkdayRate (Map<String,Integer> map);

    void setProjectChildren(Map<String,Object> map);

    void setOtherProjectNote(Map<String,Object> map);

    void setMoney(Map<String,Object> map);

    void addNumber(Project project);

    void addExcel(ExcelProject excelProject);

    void upd(Map<String,Object> map);

    void renewError(Integer id);

    void updState(Integer id);

    void spider(Project project);

    List<Map<String,Object>> sameName(Integer id);

    void sameNameInsert( Map<String,Object> map);

    Map queryById(@Param("userId") Integer userId,@Param("id") Integer id);

    List<Map<String,Object>> queryChildren(Integer id);

    Integer queryByNumber(@Param("id") Integer id ,@Param("number") String number);

    List<Map<String,Object>> queryTecById(Integer id);

    List<Map<String,Object>> queryBySelfCheck(Integer id);

    Map<String,Object> querySelfPbyId(Map<String,Object> map);

    Map<String,Object> querySelfChildrenById(Map<String,Object> map);

    List<Map<String,Object>> queryTecPrincipal(@Param("id")Integer id);

    Double completeUsed(@Param("userId")Integer userId, @Param("id") Integer id, @Param("tec")String tec);

    List<Map<String,Object>> queryIncomByProjectId(@Param("userId")Integer userId, @Param("id") Integer id, @Param("tec")String tec);

    List<Map<String,Object>> queryUsed(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    Map<String,Object> queryUsedByTec(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    Map<String,Object> queryByVolumeId(@Param("id") Integer id);

    List<String> queryAmountByMajor(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);

    void distributeTecWorkday(@Param("id")Integer id, @Param("map") Map map);

    void setWorkdayBackup(@Param("userId") Integer userId,
                          @Param("list") List<Map<String, Object>> list, @Param("date") String date);

    void setWorkdayBackupLog(@Param("userId") Integer userId,
                             @Param("list") List<Map<String, Object>> list, @Param("date") String date);
    //管理员查询
    List<Map> queryByAdmin(Integer userId);

    List<Map> queryCompleteByAdmin();
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

    List<Map> queryExcel(User user);

    List<Map> drawLine(Integer id);

    //用于虚拟项目关联实体
    List<Map> queryAll();

    List<Map> queryPrincipal(Integer id);

    List<Map<String,Object>> queryAllTaskByUser(@Param("id")Integer id, @Param("map") Map map);

    List<Map<String,Object>> queryPartTaskByUser(@Param("id")Integer id, @Param("map") Map map);

    List<Map> homepageProject(@Param("id")Integer id, @Param("date")String date);

    void setShow(@Param("id")Integer id,@Param("map")Map<String,Object> map);

    void setScientificShow(@Param("id")Integer id,@Param("map")Map<String,Object> map);

    List<Map> personalProject(@Param("id")Integer id);

    List<Map<String,Object>> getCheckerList(Integer type);

    List<Map<String,Object>> getOtherProjectList();

    Map<String,Object> getCheckerByProjectId(Integer id);

    void setChecker(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    void setTecChecker(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryNotDeclare(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryDeclare(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    void setDeclare(@Param("id")Integer id, @Param("list") List<Map<String,Object>> list,@Param("date")String date);

    void resetDeclare(Map<String,Object> map);

    Integer declareDay();

    void setDeclareDay(Integer day);

    List<Map<String,Object>> declareLog(@Param("userId") Integer userId,@Param("map") Map<String,Object> map );

    List<Map<String,Object>> declareLogList(@Param("id") Integer id,
                                               @Param("tec") String tec, @Param("date")String date);

    List<Map<String,Object>> notSubmitByManage(@Param("id")Integer userId, @Param("date")String date);

    List<Map<String,Object>> notSubmitByAdmin(String date);

    void backOff(@Param("userId") Integer userId,@Param("map") Map<String,Object> map );

    List<Map<String,Object>> projectProgress(Map<String,Object> map);

    List<Map<String,Object>> projectProgressById(Map<String,Object> map);

    List<Map<String,Object>> planVolume(Map<String,Object> map);

    List<Map<String,Object>> progressVolume(Map<String,Object> map);

    List<Map<String,Object>> progressIncompleteVolume(Map<String,Object> map);

}
