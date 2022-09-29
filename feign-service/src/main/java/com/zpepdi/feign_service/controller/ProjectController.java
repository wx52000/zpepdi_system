package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void  setProjectService(@Qualifier("com.zpepdi.feign_service.service.ProjectService") ProjectService projectService){
        this.projectService = projectService;
    }

    @RequestMapping("drawLine")
    public Result drawLine(@RequestHeader Integer id){
      return projectService.drawLine(id);
    }

    @RequestMapping("add")
    public Result add(@RequestBody Project project){
        return projectService.add(project);
    }

    @RequestMapping("addNumber")
    public Result addNumber(@RequestBody Project project){
      return projectService.addNumber(project);
    }

    @RequestMapping("upd")
    public Result upd(@RequestBody Project project){

        return projectService.upd(project);
    }

    @RequestMapping("updState")
    public Result updState(@RequestHeader Integer id){
        return projectService.updState(id);
    }

    @RequestMapping("spider")
    public Result spider(@RequestBody Project project){
        return projectService.spider(project);
  }

    @RequestMapping("queryById")
    public Result queryById(@UserId Integer userId, @RequestHeader Integer id){
        return projectService.queryById(userId,id);
    }

    @RequestMapping("queryHumanToBackup")
    public Result queryHumanToBackup(@UserId Integer id,@RequestBody Map<String,Object> map){
        return projectService.queryHumanToBackup(id,map);
    }

    @RequestMapping("setWorkdayBackup")
    public Result setWorkdayBackup(@UserId Integer id,@RequestHeader("backupDate") String date,
                                   @RequestBody List<Map<String, Object>> list){
        return projectService.setWorkdayBackup(id,date,list);
    }

    @RequestMapping("queryByAdmin")
    public Result queryByAdmin(@RequestBody User user){
        return projectService.queryByAdmin(user);
    }

    @RequestMapping("queryByGeneral")
    public Result queryByGeneral(@RequestBody User user){
        return projectService.queryByGeneral(user);
    }

    @RequestMapping("queryByPrincipal")
    public Result queryByPrincipal(@RequestBody User user){
        return projectService.queryByPrincipal(user);
    }

    @RequestMapping("queryProByPrincipal")
    public Result queryProByPrincipal(@RequestBody User user){
      return projectService.queryProByPrincipal(user);
    }

    @RequestMapping("queryByDesigner")
    public Result queryByDesigner(@RequestBody User user){
        return projectService.queryByDesigner(user);
    }

    @RequestMapping("queryByChecker")
    public Result queryByChecker(@RequestBody User user){
        return projectService.queryByChecker(user);
    }

    @RequestMapping("queryByHeadman")
    public Result queryByHeadman(@RequestBody User user){
        return projectService.queryByHeadman(user);
    }

//    @RequestMapping("projectExcel")
//    public Result projectExcel(HttpServletResponse response, HttpServletRequest request){
//        return projectService.projectExcel(response,request);
//    }

    @RequestMapping("queryAll")
    public Result queryAll(){
      return projectService.queryAll();
    }

    @RequestMapping("queryPrincipal")
    public Result queryPrincipal(@RequestHeader Integer id){
      return projectService.queryPrincipal(id);
    }



    @RequestMapping("homepageVolume")
    public Result homepageVolume(@UserId Integer userId, @RequestBody Map<String,String> map){
      return projectService.homepageVolume(userId,map);
    }

    @RequestMapping("homepageProject")
    public Result homepageVolume(@UserId Integer userId){
        return projectService.homepageProject(userId);
    }
}
