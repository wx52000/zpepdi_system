package com.zpepdi.eureka_client.dao;

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

    Map queryLimits(Integer id);

    void add(User user);

    void addExcel(List<ExcelData> list);

    void del(Integer id);

    void upd(Map<String,Object> user);

    void pwdReset(Integer id);

    Map<String,Object> workdayById(@Param("name") String name,@Param("date") String date);

    Map<String,Object> workdayHighById(@Param("name") String name, @Param("date") String date);

    Map<String,Object> workdayHighState3ById(@Param("name") String name, @Param("date") String date);

    Double workdayBackup(@Param("id")Integer id,@Param("date")String date);

    Double workdayVirtual(@Param("id")Integer id,@Param("date")String date);

    Double workdayActivity(@Param("id")Integer id,@Param("date")String date);

    List<Map<String,Object>> workdayLogById(@Param("id")Integer id, @Param("date")String date, @Param("name")String name);

    List<Map> query(User user);

    /*
    * 查询所有角色的工时
    */

    List<Map<String,Object>> workday(@Param("date") String date);
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
    //用于excel导入时的精确查询
    Integer selectByName(User user);

    List<Integer> queryByUsername(List<ExcelData> list);

    void paw(@Param("id") Integer id ,@Param("user") User user);

    List<Map> queryPrincipal(Integer id);

}
