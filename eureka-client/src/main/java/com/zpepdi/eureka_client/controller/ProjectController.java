package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.excel.ExcelProperty;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ProjectService;
import com.zpepdi.eureka_client.tools.Download;

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

    @RequestMapping("add")
    public Result add(@RequestBody Project project){
        projectService.add(project);
        return Result.ok();
    }

    @RequestMapping("addNumber")
    public Result addNumber(@RequestBody Project project){
      projectService.addNumber(project);
      return Result.ok();
    }

    @RequestMapping("upd")
    public Result upd(@RequestBody Project project){
        projectService.upd(project);
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

    @RequestMapping("queryById")
    public Result queryById(@UserId Integer userId, @RequestHeader Integer id){
        return Result.ok(projectService.queryById(userId,id));
    }
//    备用工时发放页面所用，查询相关人员，与该主设人所在专业的备用工时
    @RequestMapping("queryHumanToBackup")
    public Result queryHumanToBackup(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.queryHumanToBackup(id,map);
    }

    @RequestMapping("distributeTecWorkday")
    public Result distributeTecWorkday(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.distributeTecWorkday(id,map);
    }

    @RequestMapping("setManageByMajor")
    public Result setManageByMajor(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.setManageByMajor(id,map);
    }

//发放备用工时
    @RequestMapping("setWorkdayBackup")
    public Result setWorkdayBackup(@UserId Integer id,@RequestHeader("backupDate") String date,
                                   @RequestBody List<Map<String, Object>> list){
        return projectService.setWorkdayBackup(id,list,date);
    }

    @RequestMapping("queryByAdmin")
    public Result queryByAdmin(@RequestBody User user){
        return Result.ok(projectService.queryByAdmin(user));
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

    @RequestMapping("queryByHeadman")
    public Result queryByHeadman(@RequestBody User user){
        return projectService.queryByHeadman(user);
    }

    @RequestMapping("projectExcel")
    public Result projectExcel(HttpServletResponse response, HttpServletRequest request){
        String s = "";
        Integer month = Integer.valueOf(request.getParameter("month"));
        try {
            String fileName = month + "月汇总表.xlsx";
          ExcelProperty excelProperty = new ExcelProperty();
          excelProperty.ProjectExcel(projectService.queryExcel(month));
            Download.downloadFile( response , "project.xlsx" , fileName);
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

//    时间段内与个人相关的所有卷册
    @RequestMapping("homepageVolume")
    public Result homepageVolume(@UserId Integer userId, @RequestBody Map<String,String> map){
      return projectService.homepageVolume(userId,map);
    }
    //    时间段内与个人相关的所有项目
    @RequestMapping("homepageProject")
    public Result homepageProject(@UserId Integer userId){
        return projectService.homepageProject(userId);
    }
}
