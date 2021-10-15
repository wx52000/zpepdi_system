package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProjectExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("projectExcel")
public class ProjectExcelController {
//  @Autowired
//  private ProjectExcelService excelService;
  /*
  all为项目管理页面下载；其余为项目页面下载
  */
//  @RequestMapping("statisticAll")
//  public Result statisticAll(HttpServletResponse response, HttpServletRequest request){
//    return excelService.statisticAll(response,request);
//  }
//
//
//  @RequestMapping("statistic")
//  public Result statistic(HttpServletResponse response, HttpServletRequest request){
//    return excelService.statistic(response,request);
//  }
//
//  @RequestMapping("everyoneAll")
//  public Result everyoneAll(HttpServletResponse response, HttpServletRequest request){
//    return excelService.everyoneAll(response,request);
//  }
//
//  @RequestMapping("everyone")
//  public Result everyone(HttpServletResponse response, HttpServletRequest request){
//    return excelService.everyone(response,request);
//  }
//
//
//  @RequestMapping("volumeAll")
//  public Result volumeAll(HttpServletResponse response, HttpServletRequest request){
//    return excelService.volumeAll(response,request);
//  }
//
//  @RequestMapping("volume")
//  public Result volume(HttpServletResponse response, HttpServletRequest request){
//    return excelService.volume(response,request);
//  }
//  /*
//  <=============================================>
//   */
//
//  @RequestMapping("personal")
//  public Result personal(HttpServletResponse response, HttpServletRequest request){
//    return excelService.personal(response,request);
//  }
//  @RequestMapping("personalVolume")
//  public Result personalVolume(HttpServletResponse response, HttpServletRequest request){
//    return excelService.personalVolume(response,request);
//  }
}
