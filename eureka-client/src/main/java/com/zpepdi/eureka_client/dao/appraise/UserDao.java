package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ExcelData;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.UserOut;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    User queryById(Integer id);


    List<Map<String,Object>> queryByUserIdSameDep(@Param("id") Integer id,@Param("name") String name);

    Map queryLimits(Integer id);

    void add(Map<String,Object> map);

    void addExcel(List<ExcelData> list);

    List<Map<String,Object>> queryByProject(Integer id);

    void del(Integer id);

    void upd(Map<String,Object> user);

    void pwdReset(Integer id);

    // 根据项目角色获取对应项目数
    List<Map<String,Object>> projectRole();

    List<Map<String,Object>> manageProjectRole(Integer id);


    //根据用户id查询做设总的项目
    List<Map<String,Object>> projectGeneral(Integer id);

    List<Map<String,Object>> projectPrincipal(Integer id);

    List<Map<String,Object>> volumeDesigner(Integer id);

    List<Map<String,Object>> volumeChecker(Integer id);

    List<Map<String,Object>> volumeHeadman(Integer id);

    Map<String,Object> information(@Param("id") Integer id, @Param("map")Map<String,Object> map);

    //个人工时日志
    List<Map<String,Object>> workdayLogById(@Param("userId")Integer userId, @Param("map")Map<String,Object> map);
    //个人工时日志包含奖惩
    List<Map<String,Object>> workdayAllLogById(Map<String,Object> map);

    List<Map> query(User user);
    //条件查询
    List<Map> querylimits(User user);

    /*
    * 查询所有角色的工时
    */

    List<Map<String,Object>> workday(Map<String,Object> map);

    List<Map<String,Object>> conditionalWorkday(@Param("list") String list, @Param("map") Map<String,Object> map);

    List<Map<String,Object>> workdayByManager(@Param("id")Integer id, @Param("map")Map<String,Object> map);

    List<Map<String,Object>> workdayByGeneral( @Param("userId") Integer userId,
                                                @Param("map") Map<String,Object> map);

    List<Map<String,Object>> logByGeneral(@Param("id")Integer id, @Param("map")Map<String,Object> map);

    List<Map<String,Object>> workdayByPrincipal(@Param("date") String date, @Param("userId") Integer userId,
                                     @Param("id") Integer id);

    List<Map<String,Object>> workdayByTec(@Param("map") Map<String,Object> map, @Param("userId") Integer userId);

    List<Map<String,Object>> logByPrincipal(@Param("id")Integer id, @Param("map")Map<String,Object> map);

    List<Map<String,Object>> logByTec(@Param("id")Integer id, @Param("map")Map<String,Object> map);

    List<Map<String,Object>> workdayByProject(@Param("date") String date,
                                                @Param("id") Integer id);
    /*
    * end*/

    //查询有权限打分的人员名单
    List<String> queryGrade();
    //根据工号排序
    List<Map> queryAll();

    Map queryToupd(Integer id);

    List<Map> queryPosition(Integer id);

    //根据专业查询
    List<Map> queryByTid(Integer id);

    //根据专业查询，用于前端transfer,tree的数据
    List<Map> queryByT(@Param("id") Integer id , @Param("mode") Integer mode);

    List<Map> queryByTAndState(@Param("id") Integer id,@Param("tid")Integer tid);

    //初设前期项目
    List<Map> queryByTAndGroup(@Param("tid")Integer tid,@Param("vid")Integer vid);
    List<Integer> getGroup(@Param("id")Integer id,@Param("vid")Integer vid);
    //活动
    List<Map> queryByTAndGroup1(@Param("tid")Integer tid,@Param("vid")Integer vid);
    List<Integer> getGroup1(@Param("id")Integer id,@Param("vid")Integer vid);
    //主任查询
    List<Map> queryByDirector(User user);
    //经理查询
    List<Map> queryByManager(Map<String,Object> map);
    List<String> queryByManagerList(User user);
    List<String> queryByHeadmanList(User user);
    List<Map> queryNotSelf(Map<String,Object> map);
    List<String> queryNotSelfList(User user);
    List<Map> queryByGAndP(Map<String,Object> map);

    List<String> queryByGAndPList(User user);
    List<Map> queryNotScore(User user);
    //可评价的人员名单
    List<Map> queryAppriseAll(User user);
    //已进行评价的人员名单
    List<UserOut> queryAppraise(User user);
    //没有进行评价的人员名单
    List<UserOut> queryNotAppraise(User user);
    //没有被评价过的人员名单
    List<UserOut> queryNotScored(User user);
    //没有对专业级进行打分的人员名单
    List<UserOut> queryNotTecApp(User user);

    List<Map> queryByTec(Integer id);
    //用于设总选择时模糊查询
    List<Map> queryByName(User user);

    List<Map<String,Object>> queryTask(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);
    //用于excel导入时的精确查询
    Integer selectByName(User user);

    List<Integer> queryByUsername(List<ExcelData> list);

    void paw(@Param("id") Integer id ,@Param("user") User user);

    List<Map> queryPrincipal(Integer id);



    int addmanageUserid(Map<String,Object> map);

    void delManageUser(Integer manageuserid);
}
