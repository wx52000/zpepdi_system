package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.ExcelProject;
import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.ProjectFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = ProjectFallbackService.class)
public interface ProjectService {

    @RequestMapping("project/drawLine")
    Result drawLine(@RequestHeader("id") Integer id);

    @RequestMapping("project/add")
    Result add(@RequestBody Project project);

    @RequestMapping("project/addNumber")
    Result addNumber(@RequestBody Project project);

    @RequestMapping("project/upd")
    Result upd(@RequestBody Project project);

    @RequestMapping("project/updState")
    Result updState(@RequestHeader("id") Integer id);

    @RequestMapping("project/spider")
    Result spider(@RequestBody Project project);

    @RequestMapping("project/queryById")
    Result queryById(@RequestHeader("userId") Integer userId,@RequestHeader("id") Integer id);

    @RequestMapping("project/queryHumanToBackup")
    Result queryHumanToBackup(@RequestHeader("userId") Integer id,@RequestBody Map<String,Object> map);

    @RequestMapping("project/setWorkdayBackup")
    Result setWorkdayBackup(@RequestHeader("userId") Integer id,@RequestHeader("backupDate") String date,
                                   @RequestBody List<Map<String, Object>> list);

    @RequestMapping("project/queryByAdmin")
    Result queryByAdmin(@RequestBody User user);

    @RequestMapping("project/queryByGeneral")
    Result queryByGeneral(@RequestBody User user);

    @RequestMapping("project/queryByPrincipal")
    Result queryByPrincipal(@RequestBody User user);

    @RequestMapping("project/queryProByPrincipal")
    Result queryProByPrincipal(@RequestBody User user);

    @RequestMapping("project/queryByDesigner")
    Result queryByDesigner(@RequestBody User user);

    @RequestMapping("project/queryByChecker")
    Result queryByChecker(@RequestBody User user);

    @RequestMapping("project/queryByHeadman")
    Result queryByHeadman(@RequestBody User user);

//    @RequestMapping("projectExcel")
//    Result projectExcel(HttpServletResponse response, HttpServletRequest request);

    @RequestMapping("project/queryAll")
    Result queryAll();

    @RequestMapping("project/queryPrincipal")
    Result queryPrincipal(@RequestHeader("id") Integer id);

    @RequestMapping("project/homepageVolume")
    Result homepageVolume(@RequestHeader("userId") Integer userId,@RequestBody Map<String,String> map);

    @RequestMapping("project/homepageProject")
    Result homepageProject(@RequestHeader("userId") Integer userId);
}
