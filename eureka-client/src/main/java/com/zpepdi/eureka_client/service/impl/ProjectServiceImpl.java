package com.zpepdi.eureka_client.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpepdi.eureka_client.dao.appraise.*;
import com.zpepdi.eureka_client.dao.zjepdi.ZJEPDIDataTransmissionDao;
import com.zpepdi.eureka_client.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zpepdi.eureka_client.entity.*;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectService;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service("ProjectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao;
    private UserDao userDao;
    private ProjectTecDao projectTecDao;
    private TechnologyDao technologyDao;
    private ProjectWorkdayDao projectWorkDayDao;
    @Autowired
    private ZJEPDIDataTransmissionDao zjepdiDataTransmissionDao;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private ProjectTaskDao projectTaskDao;
    @Autowired
    public void setProjectDao(ProjectDao projectDao){
        this.projectDao = projectDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void  setProjectTecDao(ProjectTecDao projectTecDao){
        this.projectTecDao = projectTecDao;
    }
    @Autowired
    public void setTechnologyDao(TechnologyDao technologyDao){
        this.technologyDao = technologyDao;
    }
    @Autowired
    public void setProjectWorkDayDao(ProjectWorkdayDao projectWorkDayDao) {
        this.projectWorkDayDao = projectWorkDayDao;
    }

    @Override
    public Result queryUser(Integer userId, Integer id) {
        return Result.ok(projectDao.queryUser(userId,id));
    }

    @Override
    public Result setProject(Integer id, Project project) {
        project.setNumber(project.getNumber().replaceAll(" ",""));
        projectDao.setProject(id,project);
        if (project.getId() != null && project.getWorkday() != 0){
            Map<String,Object> map = new HashMap<>();
            map.put("project_id",project.getId());
            map.put("num",project.getWorkday());
            map.put("limit",project.getLimit());
            projectWorkDayDao.setProWorkday(map);
        }
        return Result.ok();
    }

    @Override
    public Result getOtherProjectN(Integer id, Map<String, Object> map) {
//        try {
//            Calendar calendar = Calendar.getInstance();
//            String yearMonth = calendar.get(Calendar.YEAR) + "" + (calendar.get(Calendar.MONTH) + 1);
//            String number = map.get("abbreviate").toString() +
//                    TaskNumberUtils.getFirstSpell(map.get("tec").toString())
//                    + yearMonth +
//                    Integer.valueOf((int) ((Math.random() * 9 + 1) * 100)).toString();
//            String name = map.get("name").toString() + "项目" + map.get("tec") + yearMonth;
//            Map<String, Object> map1 = new HashMap<>();
//            map1.put("number", number);
//            map1.put("name", name);
//            return Result.ok(map1);
//        }catch (Exception e){
//            return Result.build(785,"外部门或专业为空");
//        }
        return Result.ok();
    }

    @Override
    public Result setOtherProject(Integer id, Map<String, Object> map) {
        String officeAbbr = map.get("abbreviate").toString();
        String type = map.get("type").toString();
        JSONObject handlerNote = JSON.parseObject(JSONObject.toJSONString(map.get("handlerNote")));
        Date date = new Date();
        String YearMonth = DateUtils.dateToString(date,"yyyyMM");
        User user = userDao.queryById(id);
        map.put("general", user.getName());
        map.put("generalId", id);
        map.put("handler", handlerNote.get("id"));
        if (map.get("scope") != null){
            String scope = (map.get("scope").toString());
            map.put("scope",scope.substring(1,(scope.length()-1)));
        }
//        判断是否是发电
        if (officeAbbr.equals("FD")){
            if (map.get("projectId") == null && projectDao.queryByNameAndNum(map)){
                return Result.build(864,"该项目已建立");
            }
            map.put("checkOther", 1);
            if (type.equals("0")) {
                map.put("typeNote",0);
//                cmdMethod(map.get("number").toString(),"0");
                Map<String,Object> spiderMap = zjepdiDataTransmissionDao.queryProjectVal(map.get("number").toString());
                if (spiderMap != null && spiderMap.size() > 0){
                    map.put("spiderMoney",spiderMap.get("val"));
                    map.put("spiderRatio",spiderMap.get("DeptValueRate"));
                }else {
                    map.put("spiderMoney",0);
                    map.put("spiderRatio",0);
                }
                map.put("spider",1);
            }else {
                if (map.get("projectId") == null) {
                    map.put("name", map.get("name").toString() + YearMonth);
                }
                map.put("handler", handlerNote.get("user_id"));
                map.put("did",handlerNote.get("did"));
                map.put("typeNote",1);
            }
            projectDao.setOtherProject(id, map,1);
            projectDao.setOtherProjectNote(map);
        }else {
            if (type.equals("0")) {
                if (map.get("tec") != null && !map.get("tec").equals("综合")) {
                    String number = officeAbbr +
                            TaskNumberUtils.getFirstSpell(map.get("tec").toString())
                            + YearMonth;
                    Integer projectId;
                    if (map.get("projectId") != null && !map.get("projectId").equals("")) {
                        projectId = Integer.valueOf(map.get("projectId").toString());
                    } else {
                        projectId = projectDao.queryByNumber(id, number);
                    }
                    String projectNumber = map.get("number").toString();
                    String projectName = map.get("name").toString();
                    map.put("cNumber", projectNumber);
                    map.put("cName", projectName);
                    if (projectId != null) {
                        map.put("projectId", projectId);
                    } else {
                        String name = map.get("dep").toString() + "项目" + map.get("tec") + YearMonth;
                        map.put("number", number);
                        map.put("name", name);
                        projectDao.setOtherProject(id, map, 2);
                    }
                    //从院网数据库抓取产值
                    Map<String,Object> spiderMap = zjepdiDataTransmissionDao.queryProjectVal(projectNumber);
                    map.put("spider",1);
                    if (spiderMap != null && spiderMap.size() > 0){
                        map.put("spiderMoney",spiderMap.get("val"));
                        map.put("spiderRatio",spiderMap.get("DeptValueRate"));
                    }else {
                        map.put("spiderMoney",0);
                        map.put("spiderRatio",0);
                    }
                    Map<String,Integer> typeMap = new HashMap<>();
                    typeMap.put("stage",0);
                    typeMap.put("grade",0);
                    if(map.get("departmentId").toString().equals("2")){
                        typeMap.put("type",0);
                        int indexKV = projectName.toUpperCase().indexOf("KV");
                        if (indexKV == -1){
                            indexKV = projectName.indexOf("千伏");
                        }
                        String kv = "0";
                        if (indexKV >= 4) {
                            kv = projectName.substring(indexKV-4,indexKV);
                        }else if (indexKV > 0){
                            kv = projectName.substring(0,indexKV);
                        }
                        kv.replace("/[^0-9]/ig", "");
                        int kvInt = Integer.parseInt(kv);
                        if (kvInt >= 500){
                            typeMap.put("grade",0);
                        }
                        if (projectNumber != null && !projectNumber.equals("")){
                            int len = projectNumber.length();
                            String stageChar = projectNumber.substring(len-1).toUpperCase();
                            if ( !stageChar.equals("S")&& !stageChar.equals("Z")){
                                typeMap.put("stage",1);
                            }
                        }else {
                            return Result.build(3323,"卷册编号不能为空");
                        }
                    }else if(map.get("departmentId").toString().equals("3")){
                        typeMap.put("type",1);
                    }else {
                        typeMap.put("type",2);
                    }
                    int valWorkdayRate = projectDao.valWorkdayRate(typeMap);
                    map.put("calculationRatio",valWorkdayRate);
                    double calculation = Double.parseDouble(map.get("spiderMoney").toString())/valWorkdayRate;
                    map.put("calculation",calculation);
                    List<Map<String,Object>> tecList = zjepdiDataTransmissionDao.queryTecList(projectNumber);
                    if (tecList != null && tecList.size() > 0){
                        if (map.get("tec").toString().equals("综合")) {
                            map.put("thisCalculation", calculation);
                        }else {
                            map.put("thisCalculation", calculation/tecList.size());
                        }
                    }
                    map.put("spider",1);
                    projectDao.setProjectChildren(map);
                }else {
                    if (map.get("projectId") == null && projectDao.queryByNameAndNum(map)){
                        return Result.build(864,"该项目已建立");
                    }
                    map.put("checkOther", 1);
                    map.put("typeNote",3);
                    projectDao.setOtherProject(id, map, 1);
                    projectDao.setOtherProjectNote(map);
                }
            } else {
                if (map.get("projectId") == null && projectDao.queryByNameAndNum(map)){
                    return Result.build(864,"该项目已建立");
                }
                map.put("checkOther", 0);
                map.put("typeNote",2);
                projectDao.setOtherProject(id, map, 1);
                projectDao.setOtherProjectNote(map);
            }
        }
        return Result.ok();
    }

    //                    new File("G:\\python_project\\valueScrapy\\valueScrapy"));
    private void cmdMethod(String number, String type){
        Runtime run = Runtime.getRuntime();
        String cmd = "cmd /c start scrapy crawl outputValue -a number=" + number + "," + type;
        try {
           Process process =  run.exec(cmd,null,
                    new File("C:\\Users\\admin\\Desktop\\zpepdi-system\\valueScrapy\\valueScrapy"));
           process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        ProjectServiceImpl projectService = new ProjectServiceImpl();
//        projectService.cmdMethod("33-UC21571Z","0");
//    }

    @Override
    public Result setMoney(Integer id, Map<String,Object> map) {
        projectDao.setMoney(map);
        return Result.ok();
    }

    @Override
    public Result renewProject(Integer id, Map<String,Object> map) {
        if (map.get("number") != null) {
            map.put("number", map.get("number").toString().replaceAll(" ", ""));
        }
        projectDao.upd(map);
        projectWorkDayDao.setProWorkday(map);
        return Result.ok();
    }

    @Override
    public void addNumber(Project project) {
        projectDao.addNumber(project);
    }

    @Override
    public void renewError(Integer id) {
        projectDao.renewError(id);
    }

    @Override
    public void updState(Integer id) {
        projectDao.updState(id);
    }

  @Override
  public void spider(Project project) {
    projectDao.spider(project);
  }

    @Override
    public Result sameName(Integer id) {
        return Result.ok(projectDao.sameName(id));
    }

    @Override
    public Result sameNameInsert(Map<String,Object> map) {
        projectDao.sameNameInsert(map);
        return Result.ok();
    }

  @Override
    public List<Map> addExcel(ExcelProject excelProject) throws ParseException {
    projectDao.addExcel(excelProject);
    List<Map> newUser = new ArrayList<>();
    if (excelProject.getTec() != null) {
      excelProject.setTid(technologyDao.queryByName(excelProject.getTec()));
      projectTecDao.addExcel(excelProject);
      if (excelProject.getTid() == null) {
        Technology technology = new Technology();
        technology.setName(excelProject.getTec());
        technology.setDid(14);
        technologyDao.add(technology);
        excelProject.setTid(technology.getId());
      }
    }
    if (excelProject.getPlannedPublicationDate() != null && excelProject.getPlannedPublicationDate() != "") {
      if (StringUtils.isInteger(excelProject.getPlannedPublicationDate())) {
        excelProject.setPlannedPublicationDate(Time.timeToDate(excelProject.getPlannedPublicationDate()));
      }
    }
    if (excelProject.getActualPublicationDate() != null && excelProject.getActualPublicationDate() != "") {
      if (StringUtils.isInteger(excelProject.getActualPublicationDate())) {
        excelProject.setActualPublicationDate(Time.timeToDate(excelProject.getActualPublicationDate()));
      }
    }
    return  newUser;
  }

    @Override
    public Map queryById(Integer userId ,Integer id) {
        Map map = projectDao.queryById(userId,id);
        return map;
    }

    @Override
    public Result queryChildren(Integer userId, Map<String,Object> map) {
        if (map.get("type") != null && map.get("type").toString().equals("2")) {
            return Result.ok(projectDao.queryChildren(Integer.valueOf(
                    map.get("id").toString())));
        }else {
            return Result.build(588,"该项目没有子项");
        }
    }

    @Override
    public Result queryTecById(Integer userId ,Integer id) {
        return Result.ok( projectDao.queryTecById(id));
    }

    @Override
    public Result queryBySelfCheck(Integer userId) {
        return Result.ok(projectDao.queryBySelfCheck(userId));
    }

    @Override
    public Result querySelfPbyId(Map<String, Object> map) {
        String type = map.get("typeTab").toString();
        Map<String, Object> information = new HashMap<>();

        if (type.equals("2")){
            information = projectDao.querySelfChildrenById(map);
        }else {
            information = projectDao.querySelfPbyId(map);
        }
        if (information.get("scope") != null){
            String[] arr = information.get("scope").toString().split(",");
            List<String> list = new ArrayList<>(arr.length);
            Collections.addAll(list,arr);
            information.put("scope",list);
        }
        if (information.get("plannedDate") != null && !information.get("plannedDate").equals("")) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = null;
//            try {
//                date = dateFormat.parse(map.get("plannedDate").toString());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            information.put("plannedDate", information.get("plannedDate").toString());
        }
        return Result.ok(information);
    }


    @Override
    public Result queryDistribute(Integer userId, Map<String,Object> map) {
//        Map<String,Object> map1 =
        return Result.ok(projectDao.queryUsed(userId,map));
    }

    @Override
    public Result distributeTecWorkday(Integer userId, Map<String,Object> map) {
        Map<String,Object> map1 = projectDao.queryUsedByTec(userId, map);
        double amount = Double.parseDouble(map1.get("amount").toString());
        map1.put("backup",Double.parseDouble(map1.get("amount").toString()) -
                Double.parseDouble(map1.get("volume").toString()) - Double.parseDouble(map1.get("manage").toString()));
        double manage = 0;
        double volume = 0;
        if (map.get("manage") != null && map.get("manage") != ""){
            manage = Double.parseDouble(map.get("manage").toString());
        }
        if (map.get("volume") != null && map.get("volume") != ""){
            volume = Double.parseDouble(map.get("volume").toString());
        }
        if (Double.parseDouble(map1.get("manageUsed").toString()) > manage){
            return Result.build(500,"管理工时总数低于已分配工时");
        }
        boolean isUpdate = false;
        if (map.get("list") != null && !map.get("list").toString().equals("[]")){
            isUpdate = true;
            int count = 0;
            double volumeUsed = 0;
            JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(map.get("list")));
            for (Object o : jsonArray){
                JSONObject object = JSONObject.parseObject(o.toString());
                if (object.get("workday") != null && !object.get("workday").toString().equals("")){
                    volumeUsed += Double.parseDouble(object.get("workday").toString());
                }
                if (object.get("submit") != null && (object.get("submit").toString().equals("1") ||object.get("submit").toString().equals("2"))){
                    count ++;
                }
            }
            if ( volumeUsed > volume){
                return Result.build(500,"卷册工时总数低于卷册已分配工时");
            }
            if (count == jsonArray.size()){
                isUpdate = false;
            }
        }
        if (map1.get("mold").toString().equals("1")){
            map.put("check",1);
        }
        double used = Double.parseDouble(map1.get("backupUsed").toString());
        if (amount >= (manage + volume + used)) {
            projectDao.distributeTecWorkday(userId,map);
            projectWorkDayDao.setTecVolumeRatio(userId,map);
            if (isUpdate){
                projectWorkDayDao.setVolumeWorkday(
                        map,userId, DateUtils.getDateMonth());
            }
        }else {
            return Result.build(500,"超出工时上限");
        }
        return Result.ok();
    }


    @Override
    public Result queryUsedByTec(Integer id, Map<String, Object> map) {
        AtomicReference<String> atomicReference = new AtomicReference<>(TaskNumberUtils.number(
                map.get("proNumber").toString(),map.get("tec").toString(), map.get("type").toString()));
        Thread thread = new Thread(() -> {
            while (projectTaskDao.taskExist(atomicReference.get())){
                atomicReference.set(TaskNumberUtils.number(
                        map.get("proNumber").toString(),map.get("tec").toString(), map.get("type").toString()));
            }
        });
        thread.start();
        Map<String,Object> map1 = new HashMap<>();
        map1 = projectDao.queryUsedByTec(id,map);
        String usedField = "";
        String numField = "";
        if (map.get("type").equals(0)){
            usedField = "manageUsed";
            numField = "manage";
        }else{
            usedField = "backupUsed";
            numField = "backup";
            map1.put("backup",Double.parseDouble(map1.get("amount").toString()) -
                    Double.parseDouble(map1.get("volume").toString()) - Double.parseDouble(map1.get("manage").toString()));
        }
        double used = Double.parseDouble(map1.get(usedField).toString());
        double num = Double.parseDouble(map1.get(numField).toString());
        Map<String,Object> map2 = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.00");
        map2.put("usable", df.format(num - used));
        map2.put("check", map1.get("check"));
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        map2.put("number", atomicReference.get());
        return Result.ok(map2);
    }

    @Override
    public Result queryUsableByTec(Integer id, Map<String, Object> map) {
        Map<String,Object> map1 = projectDao.queryUsedByTec(id,map);
        map1.put("manageUsable", sub(map1.get("manage"),map1.get("manageUsed")));
        map1.put("volumeUsable", sub(map1.get("volume"),map1.get("volumeUsed")));
        map1.put("backupUsable", sub(sub(
                sub(map1.get("amount"),map1.get("backupUsed"))
                        ,map1.get("manage")),map1.get("volume")));
        return Result.ok(map1);
    }

    private double sub(Object a, Object b){
        return Double.parseDouble(a.toString()) - Double.parseDouble(b.toString());
    }

//    @Override
//    public Result setWorkdayBackup(Integer userId, List<Map<String, Object>> list,String date) {
//        Map<String,Object> map = new HashMap();
//        map.put("id", list.get(0).get("project_id"));
//        String amount = projectDao.queryAmountByMajor(userId, map);
//        Map map1 = projectDao.queryUsed(userId, map);
//        double old;
//        double num = 0;
//        double used = 0;
//        double manage = 0;
//        double volume = 0;
//        old = list.stream().filter(m -> m.get("old") != null && m.get("old") != "").mapToDouble(m -> Double.parseDouble(m.get("old").toString())).sum();
//        num = list.stream().filter(m -> m.get("workday") != null && m.get("workday") != "").mapToDouble(m -> Double.parseDouble(m.get("workday").toString())).sum();
//        if (map1 != null){
//            manage  = Double.parseDouble(map1.get("manage").toString());
//            volume  = Double.parseDouble(map1.get("volume").toString());
//            used  = Double.parseDouble(map1.get("used").toString());
//        }
//        if (Double.parseDouble(amount) >= (manage + volume + used + num - old)) {
//            projectDao.setWorkdayBackup(userId, list, date);
//        }else {
//            return Result.build(500,"超出工时上限");
//        }
//        new Thread(() -> projectDao.setWorkdayBackupLog(userId, list, date)).start();
//        return Result.ok();
//    }

    @Override
    public List<Map> queryByAdmin(Integer userId) {
        return projectDao.queryByAdmin(userId);
    }

    @Override
    public List<Map> queryCompleteByAdmin(User user) {
        return projectDao.queryCompleteByAdmin();
    }

    @Override
    public List<Map> queryByGeneral(User user) {
        user = userDao.queryById(user.getId());
        return projectDao.queryByGeneral(user);
    }

    @Override
    public List<Map> queryByPrincipal(User user) {
        user = userDao.queryById(user.getId());
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long min = calendar.getTimeInMillis();
        calendar.set(Calendar.MONTH,++month);
        long max = calendar.getTimeInMillis();
        user.setMonthMin(min);
        user.setMonthMax(max);
        return projectDao.queryByPrincipal(user);
    }

    @Override
    public List<Map> queryProByPrincipal(User user) {
      user = userDao.queryById(user.getId());
      return projectDao.queryProByPrincipal(user);
    }

  @Override
    public List<Map> queryByDesigner(User user) {
        user = userDao.queryById(user.getId());
        return projectDao.queryByDesigner(user);
    }

    @Override
    public List<Map> queryByChecker(User user) {
        user = userDao.queryById(user.getId());
        return projectDao.queryByChecker(user);
    }

    @Override
    public List<ExcelProject> queryExcel(Integer month) {
        User user = new User();
        user.setThisMonth(month);
        Calendar calendar1 = Calendar.getInstance();
        if (calendar1.get(Calendar.MONTH) == 0){
          if (month == 0){
            user.setThisMonth(12);
            user.setThisYear(calendar1.get(Calendar.YEAR)-1);
          }
          else if (month == -1){
            user.setThisMonth(11);
            user.setThisYear(calendar1.get(Calendar.YEAR)-1);
          }
          else {
            user.setThisMonth(month);
            user.setThisYear(calendar1.get(Calendar.YEAR));
          }
        }
        else if (calendar1.get(Calendar.MONTH) == 1){
          if (month == 0){
            user.setThisMonth(12);
            user.setThisYear(calendar1.get(Calendar.YEAR)-1);
          }
          else {
            user.setThisMonth(month);
            user.setThisYear(calendar1.get(Calendar.YEAR));
          }
        }
        else user.setThisMonth(month);
        calendar1.set(Calendar.MONTH,month-1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        long min = calendar1.getTimeInMillis();
        calendar1.set(Calendar.MONTH,month);
        long max = calendar1.getTimeInMillis();
        user.setMonthMin(min);
        user.setMonthMax(max);
        user.setSqlDate(user.getThisYear() + "-" + user.getThisMonth() + "-31");
        List<Map> maps = projectDao.queryExcel(user);
        List<ExcelProject> list = new ArrayList<>();
        for (Map map : maps){
            ExcelProject excelProject = new ExcelProject();
            excelProject.setNumber(map.get("number").toString());
            excelProject.setVolumeName(map.get("volumeName").toString());
            excelProject.setProjectName(map.get("projectName").toString());
            if (map.get("tec") != null) {
                excelProject.setTec(map.get("tec").toString());
            }
//            if (map.get("planned_publication_date") != null) {
//                if (StringUtils.isInteger(map.get("planned_publication_date").toString())){
//                    excelProject.setPlannedPublicationDate(Time.longToString(map.get("planned_publication_date").toString()));
//                }else
                    excelProject.setPlannedPublicationDate(map.get("planned_publication_date").toString());
//            }
//            if (map.get("actual_publication_date") != null) {
//                if (StringUtils.isInteger(map.get("actual_publication_date").toString())) {
//                    excelProject.setActualPublicationDate(Time.longToString(map.get("actual_publication_date").toString()));
//                }else
                    excelProject.setActualPublicationDate(map.get("actual_publication_date").toString());
                    excelProject.setPlanned_shot_date(map.get("planned_shot_date").toString());
//            }
//            if (map.get("professional_date") != null) {
//                if (StringUtils.isInteger(map.get("professional_date").toString())) {
//                    excelProject.setProfessionalDate(Time.longToString(map.get("professional_date").toString()));
//                }else
//                    excelProject.setProfessionalDate(map.get("professional_date").toString());
//            }
//            if (map.get("withdrawal_date") != null) {
//                if (StringUtils.isInteger(map.get("withdrawal_date").toString())) {
//                    excelProject.setWithdrawalDate(Time.longToString(map.get("withdrawal_date").toString()));
//                }else
//                    excelProject.setWithdrawalDate(map.get("withdrawal_date").toString());
//            }
            if (map.get("designer") != null) {
                excelProject.setDesigner(map.get("designer").toString());
            }
//            if (map.get("shot_date") != null) {
//                if (StringUtils.isInteger(map.get("shot_date").toString())) {
//                    excelProject.setShotDate(Time.longToString(map.get("shot_date").toString()));
//                }else
                    excelProject.setShotDate(map.get("shot_date").toString());
//            }
            Calendar calendar = Calendar.getInstance();
            //主要用于月份判断,所有不需要再加1
            Integer nowMonth = calendar.get(Calendar.MONTH);
            //判断当前月为1月或2月时下载上一年的数据
            if (nowMonth == 1 ){
                if (month == 12 || month == 11)
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
            }
            if (nowMonth == 2 ){
                if (month == 12)
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
            }
            int week ;
            if (nowMonth.equals(month)) {
                week = calendar.get(Calendar.WEEK_OF_MONTH);
            }else {
                week = calendar.getActualMaximum(month);
            }
            if (map.get("designerList") != null && map.get("designerList") != null) {
                String []designerData = map.get("designerList").toString().split("##");
                int designerLength;
                if (designerData.length >= 6){
                    designerLength = 6;
                }else {
                    designerLength = designerData.length;
                }
                for (int i = 0 ; i < designerLength ; i++ ) {
                    if(designerData[i] != null && !designerData[i].equals("")) {
                        String[] report = designerData[i].split("\\$",-1);
                        if (report.length >= 1 ) {
                            calendar.setTimeInMillis(Long.parseLong(report[2]));
                          System.out.println(calendar.get(Calendar.MONTH));
                            if (calendar.get(Calendar.MONTH)+1 == month) {
                                int day = calendar.get(Calendar.DAY_OF_MONTH);
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 1) {
                                    excelProject.setDesignerOne(report[0]);
                                    excelProject.setDesignerOneRemark(report[1]);
                                }
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 2) {
                                    excelProject.setDesignerTwo(report[0]);
                                    excelProject.setDesignerTwoRemark(report[1]);
                                }
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 3) {
                                    excelProject.setDesignerThree(report[0]);
                                    excelProject.setDesignerThreeRemark(report[1]);
                                }
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 4) {
                                    excelProject.setDesignerFour(report[0]);
                                    excelProject.setDesignerFourRemark(report[1]);
                                }
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 5) {
                                    excelProject.setDesignerFive(report[0]);
                                    excelProject.setDesignerFiveRemark(report[1]);
                                }
                            } else if (calendar.get(Calendar.MONTH) == month - 1) {
                                excelProject.setDesignerLastMonth(report[0] + "%");
                                break;
                            }
                        }
                    }
                }
            }
            if (map.get("checker") != null) {
                excelProject.setChecker(map.get("checker").toString());
            }
            if (map.get("checker_date") != null) {
                if (StringUtils.isInteger(map.get("checker_date").toString())) {
                    excelProject.setCheckerCompletionDate(Time.longToString(map.get("checker_date").toString()));
                }else
                    excelProject.setCheckerCompletionDate(map.get("checker_date").toString());
            }
            if (map.get("checkerList") != null && map.get("checkerList") != null) {
                String []checkerData = map.get("checkerList").toString().split("##");
                int checkLength;
                if (checkerData.length >= 3){
                    checkLength = 3;
                }else {
                    checkLength = checkerData.length;
                }
                for (int i = 0 ; i < checkLength ; i++ ) {
                    if (checkerData[i] != null) {
                        String[] report = checkerData[i].split("\\$", -1);
                        calendar.setTimeInMillis(Long.parseLong(report[2]));
                        if (calendar.get(Calendar.MONTH)+1 == month) {
                            if (calendar.get(Calendar.WEEK_OF_MONTH) == week) {
                                excelProject.setCheckerNowWeek(report[0]);
                                excelProject.setCheckerRemark(report[1]);
                            }
                            else {
                              if (excelProject.getCheckerLastWeek() == null)
                                excelProject.setCheckerLastWeek(report[0]);
                            }
                        }else if (calendar.get(Calendar.MONTH) == month - 1) {
                            excelProject.setCheckerLastMonth(report[0] + "%");
                            break;
                        }
                    }
                    }
                }
            if (map.get("principal") != null) {
                excelProject.setPrincipal(map.get("principal").toString());
            }
//            if (map.get("complete_time") != null) {
//                if (StringUtils.isInteger(map.get("complete_time").toString())){
//                    excelProject.setPrincipalCompletionDate(Time.longToString(map.get("complete_time").toString()));
//                }else
//                    excelProject.setPrincipalCompletionDate(map.get("complete_time").toString());
//            }
            if (map.get("principalList") != null && map.get("principalList") != null) {
                String []principalData = map.get("principalList").toString().split("##");
                int principalLength;
                if (principalData.length >= 3){
                    principalLength = 3;
                }else {
                    principalLength = principalData.length;
                }
                for (int i = 0 ; i < principalLength ; i++ ) {
                    if(principalData[i] != null) {
                        String[] report = principalData[i].split("\\$", -1);
                        calendar.setTimeInMillis(Long.parseLong(report[2]));
                        if (calendar.get(Calendar.MONTH) == month) {
                            if (calendar.get(Calendar.WEEK_OF_MONTH) == week) {
                                excelProject.setPrincipalNowWeek(report[0]);
                                excelProject.setPrincipalRemark(report[1]);
                            }
                            else if (excelProject.getPrincipalLastWeek() != null)
                                excelProject.setPrincipalLastWeek(report[0]);
                            }
                        }
                    }
                }
//            excelProject.setHeadman("");
//            if (map.get("headman_date") != null) {
//                if (StringUtils.isInteger(map.get("headman_date").toString())) {
//                    excelProject.setHeadmanCompletionDate(Time.longToString(map.get("headman_date").toString()));
//                }else
//                    excelProject.setHeadmanCompletionDate(map.get("headman_date").toString());
//            }
//            if (map.get("headmanList") != null && map.get("headmanList") != null) {
//                String []headmanData = map.get("headmanList").toString().split("##");
//                int headmanLength;
//                if (headmanData.length >= 3){
//                    headmanLength = 3;
//                }else {
//                    headmanLength = headmanData.length;
//                }
//                for (int i = 0 ; i < headmanLength ; i++ ) {
//                    if (headmanData != null) {
//                        String[] report = headmanData[i].split("\\$", -1);
//                        calendar.setTimeInMillis(Long.parseLong(report[2]));
//                        if (calendar.get(Calendar.MONTH) == month) {
//                            if (calendar.get(Calendar.WEEK_OF_MONTH) == week) {
//                                excelProject.setHeadmanNowWeek(report[0]);
//                                excelProject.setHeadmanRemark(report[1]);
//                            }
//                            if (calendar.get(Calendar.WEEK_OF_MONTH) == week - 1) {
//                                excelProject.setHeadmanLastWeek(report[0]);
//                            }
//                        }
//                    }
//                }
//            }
            list.add(excelProject);
        }
        return list;
    }

  @Override
  public Result drawLine(Integer id) {
    return Result.ok(projectDao.drawLine(id));
  }

  @Override
  public Result queryAll() {
    return Result.ok(projectDao.queryAll());
  }

  @Override
  public Result queryPrincipal(Integer id) {
    return Result.ok(projectDao.queryPrincipal(id));
  }

  @Override
  public Result queryTaskByUser(Integer userId, Map<String,String> map) {
        int role = projectDao.queryProjectRole(userId, Integer.valueOf(map.get("id")));
        List<Map<String,Object>> list = new ArrayList<>();
        if (role ==1 || role  == 2){
            list = projectDao.queryAllTaskByUser(userId,map);
        }else if (role == 3){
            list = projectDao.queryPartTaskByUser(userId,map);
        }else {
            Result.build(726,"暂无权限");
        }
      return Result.ok(list);
  }


    @Override
    public Result homepageProject(Integer userId) {
        return Result.ok(projectDao.homepageProject(userId,DateUtils.getDateMonth(new Date().getTime() - (3600L *24*declareDay()*1000))));
    }

    @Override
    public Result personalProject(Integer userId) {
        return Result.ok(projectDao.personalProject(userId));
    }

    @Override
    public Result setShowProject(Integer userId, Map<String,Object> map) {
        if (map.get("type").toString().equals("0")) {
            projectDao.setShow(userId, map);
        }else if (map.get("type").toString().equals("1")){
            projectDao.setScientificShow(userId, map);
        }
        return Result.ok();
    }

    @Override
    public Result getCheckerList( Integer type) {
        Object object;
        String listName;
        if (type == 0) {
            listName = "headmanList";
        }else {
            listName = "directorList";
        }
        object = redisTemplate.opsForValue().get(listName);
        if (object == null){
            object = projectDao.getCheckerList(type);
            redisTemplate.opsForValue().set(listName, JSONArray.toJSONString(object));
        }else {
            object = JSONArray.parseArray(object.toString());
        }
        return Result.ok(object);
    }

    @Override
    public Result getOtherProjectList() {
        return Result.ok(projectDao.getOtherProjectList());
    }

    @Override
    public Result getCheckerListByProjectId(Integer id) {
        Object object;
        String listName = "directorList";
        Map<String,Object> map = projectDao.getCheckerByProjectId(id);
        if (map != null && !map.isEmpty()) {
            object = redisTemplate.opsForValue().get(listName);
            if (object == null) {
                List<Map<String,Object>> list = projectDao.getCheckerList(1);
                list.add(map);
                object = list;
            } else {
                List<Object> list = JSONArray.parseArray(object.toString());
                list.add(map);
                object =list;
            }
        }else {
            return getCheckerList(1);
        }
        return Result.ok(object);
    }

    @Override
    public Result setChecker(Integer userId, Map<String, Object> map) {
        projectDao.setChecker(userId,map);
        return Result.ok();
    }

    @Override
    public Result setTecChecker(Integer userId, Map<String, Object> map) {
        projectDao.setTecChecker(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryNotDeclare(Integer userId, Map<String, Object> map) {
        return Result.ok(projectDao.queryNotDeclare(userId,map));
    }

    @Override
    public Result setDeclare(Integer userId, List<Map<String, Object>> list, String date) {
        if (list.size() > 0) {
            projectDao.setDeclare(userId, list, date);
        }else {
            return Result.build(212,"未选择需申报的任务");
        }
        return Result.ok();
    }

    @Override
    public Integer declareDay() {
        Object object = redisTemplate.opsForValue().get("declareDay");
        if (object == null){
            System.out.println("数据库查询");
            object = projectDao.declareDay();
            redisTemplate.opsForValue().set("declareDay",object);
        } else {
            System.out.println("redis查询");
        }
        return Integer.valueOf(object.toString());
    }

    @Override
    @Transactional
    public Result setDeclareDay(Integer day) {
        projectDao.setDeclareDay(day);
        redisTemplate.opsForValue().set("declareDay",day);
        return Result.ok();
    }

    @Override
    public Integer confirmDay() {
        Object object = redisTemplate.opsForValue().get("confirmDay");
        if (object == null){
            System.out.println("数据库查询");
            object = projectDao.confirmDay();
            redisTemplate.opsForValue().set("confirmDay",object);
        } else {
            System.out.println("redis查询");
        }
        return Integer.valueOf(object.toString());
    }

    @Override
    @Transactional
    public Result setConfirmDay(Integer day) {
        projectDao.setConfirmDay(day);
        redisTemplate.opsForValue().set("confirmDay",day);
        return Result.ok();
    }

    @Override
    public Result declareLog(Integer userId,Map<String,Object> map) {
        return Result.ok(projectDao.declareLog(userId,map));
    }

    @Override
    public Result notSubmitByManage(Integer id) {
        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == declareDay()) {
            return Result.ok(projectDao.notSubmitByManage(id,DateUtils.getDateMonth(new Date().getTime() - (3600L *24*declareDay()*1000))));
        }else {
            return Result.ok();
        }
    }

    @Override
    public Result notSubmitByAdmin() {
        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == declareDay()) {
            return Result.ok(projectDao.notSubmitByAdmin(DateUtils.getDateMonth(new Date().getTime() - (3600L *24*declareDay()*1000))));
        }else {
            return Result.ok();
        }
    }

    @Override
    public Result backOff(Integer userId, Map<String,Object> map){
        if (DateUtils.getDateMonth(
                new Date().getTime() - (3600L *24*declareDay()*1000))
                .equals(map.get("date").toString())){
            projectDao.backOff(userId,map);
            return Result.ok();
        }else {
            return Result.build(422,"该任务申报月份已结束，不可申请回退");
        }
    }

    @Override
    public Result projectProgress(Integer userId, Map<String,Object> map) {
        map.put("userId",userId);
        return Result.ok(projectDao.projectProgress(map));
    }

    @Override
    public Result projectProgressById(Integer userId, Map<String,Object> map) {
        map.put("userId", userId);
        return Result.ok(projectDao.projectProgressById(map));
    }

    @Override
    public Result progressVolume(Map<String, Object> map) {
        return Result.ok(projectDao.progressVolume(map));
    }

    @Override
    public Result planVolume(Map<String, Object> map) {
        return Result.ok(projectDao.planVolume(map));
    }

    @Override
    public Result progressIncompleteVolume(Map<String, Object> map) {
        return Result.ok(projectDao.progressIncompleteVolume(map));
    }

    @Override
    public Result drawlineInfo(Integer userId, Map<String, Object> map) {
        map.put("userId", userId);
        List<String> list = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");


            Date d1 = new SimpleDateFormat("yyyy-MM").parse(map.get("startMonth").toString());//定义起始日期

            Date d2 = new SimpleDateFormat("yyyy-MM").parse(map.get("endMonth").toString());//定义结束日期  可以去当前月也可以手动写日期。

            Calendar dd = Calendar.getInstance();//定义日期实例

            dd.setTime(d1);//设置日期起始时间

            list = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            while (d2.after(dd.getTime())) {//判断是否到结束日期
                String str = sdf.format(dd.getTime());
                System.out.println(str);//输出日期结果
                list.add(str);
                dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
            }
            String d3 = sdf.format(d2);
            list.add(d3);
        } catch (Exception e) {
        }
        map.put("datestring",JSONObject.toJSONString(list));
        return Result.ok(projectDao.drawlineInfo(map));
    }


    @Override
    public Result drawtotal(Map<String, Object> map) {
        List<String> list = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");


            Date d1 = new SimpleDateFormat("yyyy-MM").parse(map.get("startMonth").toString());//定义起始日期

            Date d2 = new SimpleDateFormat("yyyy-MM").parse(map.get("endMonth").toString());//定义结束日期  可以去当前月也可以手动写日期。

            Calendar dd = Calendar.getInstance();//定义日期实例

            dd.setTime(d1);//设置日期起始时间

            list = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            while (d2.after(dd.getTime())) {//判断是否到结束日期
                String str = sdf.format(dd.getTime());
                System.out.println(str);//输出日期结果
                list.add(str);
                dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
            }
            String d3 = sdf.format(d2);
            list.add(d3);
        } catch (Exception e) {
        }
        map.put("datestring",JSONObject.toJSONString(list));
        return Result.ok(projectDao.drawtotal(map));
    }

    @Override
    public Result getdateInfo(Map<String, Object> map) {

        return Result.ok(projectDao.getdateInfo(map));
    }
}
