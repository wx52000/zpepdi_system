package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProjectExcelService;
import com.zpepdi.feign_service.tools.Download;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("projectExcel")
public class ProjectExcelController {
  @Autowired
  private ProjectExcelService excelService;
  /*
  all为项目管理页面下载；其余为项目页面下载
  */
  @RequestMapping("statisticAll")
  public Result statisticAll(@RequestParam("date")String date, HttpServletResponse response) {
//      String min = request.getParameter("minDay");
//      String max = request.getParameter("maxDay");
      System.out.println("++++++++++");
      Response serviceResponse =
              excelService.statisticAll(date);
      System.out.println("_______________");
      Download.downloadFile(serviceResponse, response);
      return null;
  }


  @RequestMapping("statistic")
  public Result statistic(HttpServletResponse response, @RequestParam("id")Integer id){
      Response serviceResponse = excelService.statistic(id);
      Download.downloadFile(serviceResponse,response);
      return Result.ok();
  }

  @RequestMapping("everyoneAll")
  public Result everyoneAll(HttpServletResponse response, @RequestParam("date")String date){
      Response serviceResponse = excelService.everyoneAll(date);
      Download.downloadFile(serviceResponse,response);
      return Result.ok();
  }

  @RequestMapping("everyone")
  public Result everyone(HttpServletResponse response, @RequestParam("id")Integer id){
      Response serviceResponse = excelService.everyone(id);
      Download.downloadFile(serviceResponse,response);
      return Result.ok();
  }


  @RequestMapping("volumeAll")
  public Result volumeAll(HttpServletResponse response, @RequestParam("date")String date){
      Response serviceResponse = excelService.volumeAll(date);
      Download.downloadFile(serviceResponse,response);
      return Result.ok();
  }

  @RequestMapping("volume")
  public Result volume(HttpServletResponse response, @RequestParam("id")Integer id){
      Response serviceResponse = excelService.volume(id);
      Download.downloadFile(serviceResponse,response);
      return Result.ok();
  }
  /*
  <=============================================>
   */

  @RequestMapping("personal")
  public Result personal(HttpServletResponse response, @UserId Integer userId){
      Response serviceResponse = excelService.personal(userId);
      Download.downloadFile(serviceResponse,response);
      return Result.ok();
  }
  @RequestMapping("personalVolume")
  public Result personalVolume(HttpServletResponse response,@UserId() Integer userId,
                               @RequestParam("minDay")String min,@RequestParam("maxDay")String max){
      Response serviceResponse = excelService.personalVolume(userId,min,max);
      Download.downloadFile(serviceResponse,response);
      return Result.ok();
  }
}
