package com.zpepdi.eureka_client.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.zpepdi.eureka_client.dao.appraise.*;
import com.zpepdi.eureka_client.excel.PlanDateListener;
import com.zpepdi.eureka_client.tools.DateUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.Volume;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.VolumeService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@Transactional
public class VolumeServiceImpl implements VolumeService {
    private VolumeDao volumeDao;
    private VolumeUserDao volumeUserDao;
    private UserDao userDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectTaskDao projectTaskDao;
    @Autowired
    public void  setVolumeDao(VolumeDao volumeDao){
        this.volumeDao = volumeDao;
    }
    @Autowired
    public void  setVolumeUserDao(VolumeUserDao volumeUserDao){
        this.volumeUserDao = volumeUserDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
      this.userDao = userDao;
    }
    @Override
    public void addFormPro(Project project) {

    }

    @Override
    public Map queryById(Integer id) {
        return volumeDao.queryById(id);
    }

    @Override
    public List<Map<String, String>> queryByProjectId(Integer userId,Project project) {
        Integer role = projectDao.queryProjectRole(userId,project.getId());

        return volumeDao.queryByProjectId(project.getId());

    }

  @Override
  public List<Map<String, String>> queryByProjectId(Project project) {

        return volumeDao.queryByProjectId(project.getId());

  }

  @Override
  public List<Map<String, String>> queryByDate(String date) {
    return volumeDao.queryByDate(date);
  }

  @Override
    public void upd(Volume volume) {
        volumeDao.upd(volume);
        if(volume.getDesigner() != null ){
            volume.setPower(4);
            volumeUserDao.del(volume);
            volumeUserDao.addDesigner(volume);
        }
        if(volume.getChecker() != null ){
            volume.setPower(5);
            volumeUserDao.del(volume);
            volumeUserDao.addChecker(volume);
        }
    }

    @Override
    public void add(Volume volume) {
        Date date = new Date();
        volume.setCreatorDate(date.getTime());
        volumeDao.add(volume);
        volumeUserDao.addDesigner(volume);
        volumeUserDao.addChecker(volume);
    }

  @Override
  public List<Map> queryVolume(String user, String volume) {
    return volumeDao.queryVolume(user,volume);
  }

  @Override
  public Result queryByNumber(Volume volume) {
      User user = userDao.queryById(volume.getId());
      volume.setName(user.getName());
    return Result.ok(volumeDao.queryByNumber(volume));
  }

  @Override
  public List<Map<String, String>> personalVolume(Map<String, String> map) {
    User user = userDao.queryById(Integer.valueOf(map.get("id")));
    map.put("name",user.getName());
    return volumeDao.personalVolume(map);
  }

    @Override
    public Result setReason( Map<String, String> map) {
        volumeDao.setReason(map);
        return Result.ok();
    }

    @Override
    public Result setWorkday(Integer userId, Map<String, Object> map) {
        map.put("date", DateUtils.getDateMonth());
        Map<String, Object> map1 = projectDao.queryUsedByTec(userId,map);
        String usedField = "";
        String numField = "";
        if (map.get("type").equals("管理")){
            usedField = "manageUsed";
            numField = "manage";
        }else if (map.get("type").equals("卷册")){
            usedField = "volumeUsed";
            numField = "volume";
            map.put("typeId", 0);
        }else {
            usedField = "backupUsed";
            numField = "backup";
            map.put("typeId", 1);
            map1.put("backup",Double.parseDouble(map1.get("amount").toString()) -
                    Double.parseDouble(map1.get("volume").toString()) - Double.parseDouble(map1.get("manage").toString()));
        }
        double used = Double.parseDouble(map1.get(usedField).toString());
        double num = Double.parseDouble(map1.get(numField).toString());
        double old = 0;
        if (map.get("spider").equals(1)) {
            Map<String,Object> oldMap = projectDao.queryByVolumeId(Integer.valueOf(map.get("taskId").toString()));
            old = Double.parseDouble(oldMap.get("workday").toString());
            if (num >= (used + Double.parseDouble(map.get("workday").toString()) - old)) {
                String[] s = {"待送出版", "正在出版", "代送业主", "已完成", "院交出"};
                new Thread(() -> volumeDao.setWorkdayLog(userId, map)).start();
                map.put("state", "0");
                for (String s1 : s) {
                    if (s1.equals(map.get("state").toString())) {
                        map.put("state", "1");
                    }
                }
                double workday = Double.parseDouble(map.get("workday").toString());
                double designer = workday * Double.parseDouble(oldMap.get("designer").toString());
                double checker = workday * Double.parseDouble(oldMap.get("checker").toString());
                double principal = workday * Double.parseDouble(oldMap.get("principal").toString());
                double headman = workday * Double.parseDouble(oldMap.get("headman").toString());
                map.put("designer",designer);
                map.put("checker",checker);
                map.put("principal",principal);
                map.put("headman",headman);
                volumeDao.setWorkday(userId, map);
                map1.put("workday",workday);
                map1.put("designer",String.format("%.1f",designer));
                map1.put("checker",String.format("%.1f",checker));
                map1.put("principal",String.format("%.1f",principal));
                map1.put("headman",String.format("%.1f",headman));
            }else {
                return Result.build(566, "可用工时不足，请重新输入");
            }
        }else {
            Map<String,Object> taskMap = projectTaskDao.queryById(Integer.valueOf(map.get("taskId").toString()));
            if (taskMap != null && !taskMap.get("workday").equals(0)){
                old = Double.parseDouble(taskMap.get("workday").toString());
            }
            double workday = Double.parseDouble(map.get("designer_workday").toString()) + Double.parseDouble(map.get("principal_workday").toString())
                    + Double.parseDouble(map.get("checker_workday").toString()) + Double.parseDouble(map.get("headman_workday").toString());
            map.put("workday",workday);
            if (num >= (used + workday - old)) {
                new Thread(() -> projectTaskDao.taskLog(userId, map)).start();
                projectTaskDao.taskWorkday(map);
                map1.put("workday",workday);
                map1.put("designer",map.get("designer_workday"));
                map1.put("checker",map.get("checker_workday"));
                map1.put("principal",map.get("principal_workday"));
                map1.put("headman",map.get("headman_workday"));
                map1.put("wtid", map.get("wtid"));
            } else {
                return Result.build(566, "可用工时不足，请重新输入");
            }
        }
                map1.put("type", map.get("type"));
                map1.put("amount", num);
                map1.put("used", used +
                        Double.parseDouble(map.get("workday").toString()) - old);
                return Result.ok(map1);

    }


    @Override
    public Result setWorkdayHigh(Integer userId, Map<String, Object> map) {
            volumeDao.setWorkdayHigh(userId,map);
            return Result.ok();
    }

    @Override
    public Result setWorkdayAdvance(Integer userId, Map<String, Object> map) {
        map.put("date",DateUtils.getDateMonth());
        Map map1 = volumeDao.queryVolumeWorkdayHigh(map);
        if (map1.get("state").toString().equals("1") || map1.get("state").toString().equals("3")){
            return Result.build(666,"卷册已完成，工时将全部发放");
        }
        map.put("state",2);
        map.put("grant" ,Double.parseDouble(map1.get("designer_workday").toString()) *  Double.parseDouble(map.get("ratio").toString()));
        volumeDao.setWorkdayStateById(map);
        volumeDao.setWorkdayAdvance(userId, map);
        return Result.ok();
    }

    @Override
    public Result queryVolumeWorkday(Map<String, Object> map) {
        return Result.ok(volumeDao.queryVolumeWorkdayAndRatio(map));
    }

    @Override
    public Result queryVolumeWorkdayHigh(Map<String, Object> map) {
            return Result.ok(volumeDao.queryVolumeWorkdayHigh(map));
    }

    @Override
    public Result queryVolumeWorkdayLog(Map<String, String> map) {
        return Result.ok(volumeDao.queryVolumeWorkdayLog(map));
    }

    @Override
    public Result queryBackupWorkdayLog(Map<String, String> map) {
        return Result.ok(volumeDao.queryBackupWorkdayLog(map));
    }

    @Override
    public Result setWorkdayState(String date, Integer old, Integer now) {
        volumeDao.setWorkdayState(date,old,now);
        return Result.ok();
    }

    @Override
    public Result queryPlannedPublic(String date) {
        StringBuilder start = new StringBuilder(date);
        start.append("-01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.stringToDate(date,"yyyy-MM"));
        StringBuilder end = new StringBuilder(date);
        end.append("-");
        end.append(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.printf("start %s,end %s", start,end);
        return Result.ok(volumeDao.queryPlannedPublic(start.toString(),end.toString()));
    }

    @Override
    public Result tecProgress(Map<String, Object> map) {
        return Result.ok(volumeDao.tecProgress(map));
    }

    @Override
    public Result manageTecProgress(Integer userId, Map<String, Object> map) {
        map.put("userId",userId);
        return Result.ok(volumeDao.manageTecProgress(map));
    }

    @Override
    public Result tecVolumeCompleteByDate(Map<String, Object> map) {
        return Result.ok(volumeDao.tecVolumeCompleteByDate(map));
    }

    @Override
    public Result tecVolumeInCompleteByDate(Map<String, Object> map) {
        return Result.ok(volumeDao.tecVolumeInCompleteByDate(map));
    }

    @Override
    public Result tecVolumePlanCompleteByDate(Map<String, Object> map) {
        return Result.ok(volumeDao.tecVolumePlanCompleteByDate(map));
    }

    @Override
    public Result tecVolumeRecordByDate(Map<String, Object> map) {
        return Result.ok(volumeDao.tecVolumeRecordByDate(map));
    }

    @Override
    public Result tecProgressByProjectId(Integer userId,Map<String, Object> map) {
        map.put("userId",userId);
        return Result.ok(volumeDao.tecProgressByProjectId(map));
    }

    @Override
    public Result tecVolumeCompleteByDateByProjectId(Map<String, Object> map) {
        return Result.ok(volumeDao.tecVolumeCompleteByDateByProjectId(map));
    }

    @Override
    public Result tecVolumeInCompleteByDateByProjectId(Map<String, Object> map) {
        return Result.ok(volumeDao.tecVolumeInCompleteByDateByProjectId(map));
    }

    @Override
    public Result tecVolumePlanCompleteByDateByProjectId(Map<String, Object> map) {
        return Result.ok(volumeDao.tecVolumePlanCompleteByDateByProjectId(map));
    }

    @Override
    public Result tecVolumeRecordByDateByProjectId(Map<String, Object> map) {
        return Result.ok(volumeDao.tecVolumeRecordByDateByProjectId(map));
    }

    @Override
    public Result setPlanDate(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            PlanDateListener planDateListener = new PlanDateListener(this);
            EasyExcel.read(inputStream, planDateListener).sheet().doRead();
//        projectDao.setPlanDate(list);
            return Result.ok(planDateListener.getNotHave());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @Override
    public List<Map<String,Object>> queryNotHave(String s){
        return volumeDao.queryNotHave(s);
    }

    @Override
    public Result setPlanDate(List<Map<String, Object>> list) {
        volumeDao.setPlanDate(list);
        return Result.ok();
    }

    @Override
    public Result queryTodayEntryPlan() {
        return Result.ok(volumeDao.queryTodayEntryPlan());
    }

    @Override
    public Result queryPlannedPublicDate(String search) {
        return Result.ok(volumeDao.queryPlannedPublicDate(search));
    }

    @Override
    public Result setSinglePlanDate(Map<String, Object> map) {
        volumeDao.setSinglePlanDate(map);
        return Result.ok();
    }

    @Override
    public Result queryRecently10Day(Integer id) {
        return Result.ok(volumeDao.queryRecently10Day(id));
    }

    @Override
    public Result setPlanConfirm(Integer userId, Map<String,Object> map) {
        volumeDao.setPlanConfirm(userId,map);
        return Result.ok();
    }

    @Override
    public Result updatePlanedPublicDate() {
        Map<String,Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        //获取当前月最后一天
        calendar.add(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,0);
//        calendar.add(Calendar.DAY_OF_MONTH,-1);
        map.put("year",calendar.get(Calendar.YEAR));
        map.put("month",calendar.get(Calendar.MONTH)+1);
        String start = DateUtils.dateToString(calendar.getTime(),"yyyy-MM-01");
        String end = DateUtils.dateToString(calendar.getTime());
        //获取下月最后一天
        calendar.add(Calendar.MONTH,2);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        String date = DateUtils.dateToString(calendar.getTime());
        map.put("start",start);
        map.put("end",end);
        map.put("date",date);
        volumeDao.updatePlanedPublicDate(map);
        volumeDao.setPlanRecord(map);
        return Result.ok();
    }

    @Override
    public Result resetPlanDate() {
        Map<String,Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,0);
//        获取本月最后一天
        String date = DateUtils.dateToString(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH,0);
//        获取上月最后一天和第一天
        String start = DateUtils.dateToString(calendar.getTime(),"yyyy-MM-01");
        String end = DateUtils.dateToString(calendar.getTime());
        map.put("start",start);
        map.put("end",end);
        map.put("date",date);
        volumeDao.resetPlanDate(map);
        return Result.ok();
    }

    @Override
    public Result queryConfirmTec(Integer userId, Integer id) {
        return Result.ok(volumeDao.queryConfirmTec(userId,id));
    }

    @Override
    public Result sentConfirm(Integer userId, Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        map.put("planMonth",DateUtils.dateToString(calendar.getTime(),"yyyy-MM"));
        calendar.add(Calendar.MONTH,-1);
        map.put("workdayMonth",DateUtils.dateToString(calendar.getTime(),"yyyy-MM"));
        volumeDao.sendConfirm(userId,map);
        if (map.get("list") != null && !map.get("list").toString().equals("[]")) {
            volumeDao.sendConfirmVolume(map);
        }
        return Result.ok();
    }

    @Override
    @Transactional
    public Result timingConfirmWorkday(String date) {
        volumeDao.timingConfirmWorkday(date);
        return Result.ok();
    }
}
