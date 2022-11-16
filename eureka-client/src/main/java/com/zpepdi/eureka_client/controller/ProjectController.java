package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.excel.ExcelProperty;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectService;
import com.zpepdi.eureka_client.tools.Download;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("project")
public class ProjectController {
    private ProjectService projectService;
    @Autowired
    public void  setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }

    @RequestMapping("drawLine")
    public Result drawLine(@RequestHeader Integer id){
      return projectService.drawLine(id);
    }

    @RequestMapping("queryUser")
    public Result queryUser(@UserId Integer userId,@RequestHeader("id") Integer id){
        return projectService.queryUser(userId,id);
    }

    @RequestMapping("setProject")
    public Result setProject(@UserId Integer id,@RequestBody Project project){
        projectService.setProject(id,project);
        return Result.ok();
    }

    @RequestMapping("getOtherProjectN")
    public Result getOtherProjectN(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.getOtherProjectN(id,map);
    }

    @RequestMapping("setOtherProject")
    public Result setOtherProject(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.setOtherProject(id,map);
    }

    @RequestMapping("setMoney")
    public Result setMoney(@UserId Integer id,@RequestBody Map<String,Object> map){
        projectService.setMoney(id, map);
        return Result.ok();
    }

    @RequestMapping("renewProject")
    public Result renewProject(@UserId Integer id,@RequestBody Map<String,Object> map){
        projectService.renewProject(id,map);
        return Result.ok();
    }

    @RequestMapping("renewError")
    public Result renewError(@RequestHeader Integer id){
        projectService.renewError(id);
        return Result.ok();
    }

    @RequestMapping("addNumber")
    public Result addNumber(@RequestBody Project project){
      projectService.addNumber(project);
      return Result.ok();
    }

    @RequestMapping("updState")
    public Result updState(@RequestHeader Integer id){
        projectService.updState(id);
        return Result.ok();
    }

      @RequestMapping("spider")
      public Result spider(@RequestBody Project project){
        projectService.spider(project);
        return Result.ok();
      }

    @RequestMapping("sameName")
    public Result sameName(@UserId Integer id){
        return projectService.sameName(id);
    }

    @RequestMapping("sameNameInsert")
    public Result sameNameInsert(@RequestBody Map<String,Object> map){
        return projectService.sameNameInsert(map);
    }

    @RequestMapping("queryById")
    public Result queryById(@UserId Integer userId, @RequestHeader Integer id){
        return Result.ok(projectService.queryById(userId,id));
    }

    @RequestMapping("queryChildren")
    public Result queryChildren(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectService.queryChildren(userId,map);
    }

    @RequestMapping("queryTecById")
    public Result queryTecById(@UserId Integer userId, @RequestHeader Integer id){
        return projectService.queryTecById(userId,id);
    }

    @RequestMapping("queryBySelfCheck")
    public Result queryBySelfCheck(@UserId Integer userId){
        return projectService.queryBySelfCheck(userId);
    }

    @RequestMapping("querySelfPbyId")
    public Result querySelfPbyId(@RequestBody Map<String,Object> map){
        return projectService.querySelfPbyId(map);
    }


//    主设人工时发放页面所用，查询相关人员，与该主设人所在专业的备用工时
    @RequestMapping("queryDistribute")
    public Result queryDistribute(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.queryDistribute(id,map);
    }
//    备用工时新建任务发放
    @RequestMapping("queryUsedByTec")
    public Result queryUsedByTec(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.queryUsedByTec(id,map);
    }

//    专业查询可用工时
    @RequestMapping("queryUsableByTec")
    public Result queryUsableByTec(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.queryUsableByTec(id,map);
    }

    @RequestMapping("distributeTecWorkday")
    public Result distributeTecWorkday(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.distributeTecWorkday(id,map);
    }

//发放备用工时
//    @RequestMapping("setWorkdayBackup")
//    public Result setWorkdayBackup(@UserId Integer id,@RequestHeader("backupDate") String date,
//                                   @RequestBody List<Map<String, Object>> list){
//        return projectService.setWorkdayBackup(id,list,date);
//    }

    @RequestMapping("queryByAdmin")
    public Result queryByAdmin(@RequestBody User user){
        return Result.ok(projectService.queryByAdmin(user));
    }


    @RequestMapping("queryCompleteByAdmin")
    public Result queryCompleteByAdmin(@RequestBody User user){
        return Result.ok(projectService.queryCompleteByAdmin(user));
    }
    @RequestMapping("queryByGeneral")
    public Result queryByGeneral(@RequestBody User user){
        return Result.ok(projectService.queryByGeneral(user));
    }

    @RequestMapping("queryByPrincipal")
    public Result queryByPrincipal(@RequestBody User user){
        return Result.ok(projectService.queryByPrincipal(user));
    }

    @RequestMapping("queryProByPrincipal")
    public Result queryProByPrincipal(@RequestBody User user){
      return Result.ok(projectService.queryProByPrincipal(user));
    }

    @RequestMapping("queryByDesigner")
    public Result queryByDesigner(@RequestBody User user){
        return Result.ok(projectService.queryByDesigner(user));
    }

    @RequestMapping("queryByChecker")
    public Result queryByChecker(@RequestBody User user){
        return Result.ok(projectService.queryByChecker(user));
    }

    @RequestMapping("projectExcel")
    public Result projectExcel(HttpServletResponse response, HttpServletRequest request){
        String s = "";
        Integer month = Integer.valueOf(request.getParameter("month"));
        try {
            String fileName = month + "月汇总表.xlsx";
          ExcelProperty excelProperty = new ExcelProperty();
          excelProperty.ProjectExcel(projectService.queryExcel(month));
            Download.downloadFile( response , "D://excel/" + "project.xlsx" , fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @RequestMapping("queryAll")
    public Result queryAll(){
      return projectService.queryAll();
    }

    @RequestMapping("queryPrincipal")
    public Result queryPrincipal(@RequestHeader Integer id){
      return projectService.queryPrincipal(id);
    }

//    时间段内与个人相关的所有
    @RequestMapping("queryTaskByUser")
    public Result queryTaskByUser(@UserId Integer userId, @RequestBody Map<String,String> map){
      return projectService.queryTaskByUser(userId,map);
    }
    //    个人相关的所有项目
    @RequestMapping("homepageProject")
    public Result homepageProject(@UserId Integer userId){
        return projectService.homepageProject(userId);
    }

    @RequestMapping("PersonalProject")
    public Result PersonalProject(@UserId Integer userId){
        return projectService.personalProject(userId);
    }
//    修改项目页面显示状态
    @RequestMapping("setShowProject")
    public Result setShowProject(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectService.setShowProject(userId,map);
    }

    @RequestMapping("getCheckerList")
    public Result getCheckerList(@RequestHeader Integer type){
        return projectService.getCheckerList(type);
    }
    //无产值项目列表
    @RequestMapping("getOtherDeoProjectList")
    public Result getOtherDeoProjectList(@RequestHeader Integer type){
        return projectService.getOtherDeoProjectList(type);
    }

    @RequestMapping("getCheckerListByProjectId")
    public Result getCheckerListByProjectId(@RequestHeader Integer id){
        return projectService.getCheckerListByProjectId(id);
    }

    @RequestMapping("setChecker")
    public Result setChecker(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return projectService.setChecker(userId,map);
    }

    @RequestMapping("setTecChecker")
    public Result setTecChecker(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return projectService.setTecChecker(userId,map);
    }

    @RequestMapping("queryNotDeclare")
    public Result queryNotDeclare(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return projectService.queryNotDeclare(userId,map);
    }

    @RequestMapping("setDeclare")
    public Result setDeclare(@UserId Integer userId,@RequestBody List<Map<String,Object>> list, @RequestHeader("declareDate")String date){
        return projectService.setDeclare(userId,list,date);
    }

    @RequestMapping("declareDay")
    public Result declareDay(){
        return Result.ok(projectService.declareDay());
    }

    @RequestMapping("setDeclareDay")
    public Result setDeclareDay(@RequestHeader("day") Integer day){
        return projectService.setDeclareDay(day);
    }

    @RequestMapping("declareLog")
    public Result declareLog(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectService.declareLog(userId,map);
    }

    @RequestMapping("notSubmitByManage")
    public Result NotSubmitByManage(@UserId Integer userId){
        return projectService.notSubmitByManage(userId);
    }

    @RequestMapping("notSubmitByAdmin")
    public Result NotSubmitByAdmin(@UserId Integer userId){
        return projectService.notSubmitByAdmin();
    }

//    主设人请求回退
    @RequestMapping("backOff")
    public Result backOff(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return projectService.backOff(userId,map);
    }

    @RequestMapping("projectProgress")
    public Result projectProgress(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return projectService.projectProgress(userId,map);
    }

    @RequestMapping("projectProgressById")
    public Result projectProgressById(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return projectService.projectProgressById(userId,map);
    }

    @RequestMapping("progressVolume")
    public Result progressVolume(@RequestBody Map<String, Object> map) {
        return projectService.progressVolume(map);
    }



    @RequestMapping("planVolume")
    public Result planVolume(@RequestBody Map<String, Object> map) {
        return projectService.planVolume(map);
    }

    @RequestMapping("progressIncompleteVolume")
    public Result progressIncompleteVolume(@RequestBody Map<String, Object> map) {
        return projectService.progressIncompleteVolume(map);
    }


}
