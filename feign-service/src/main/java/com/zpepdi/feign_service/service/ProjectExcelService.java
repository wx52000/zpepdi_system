package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.ProjectExcelTec;
import com.zpepdi.feign_service.fallback.ProjectExcelFallbackService;
import com.zpepdi.feign_service.fallback.ProjectFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@FeignClient(value = "EUREKA-CLIENT", fallback = ProjectExcelFallbackService.class)
public interface ProjectExcelService {

//    @RequestMapping("projectExcel/statisticAll")
//    Result statisticAll(HttpServletResponse response, HttpServletRequest request);
//
//    @RequestMapping("projectExcel/statistic")
//    Result statistic(HttpServletResponse response, HttpServletRequest request);
//
//    @RequestMapping("projectExcel/everyoneAll")
//    Result everyoneAll(HttpServletResponse response, HttpServletRequest request);
//
//    @RequestMapping("projectExcel/everyone")
//    Result everyone(HttpServletResponse response, HttpServletRequest request);
//
//    @RequestMapping("projectExcel/volumeAll")
//    Result volumeAll(HttpServletResponse response, HttpServletRequest request);
//
//    @RequestMapping("projectExcel/volume")
//    Result volume(HttpServletResponse response, HttpServletRequest request);
//
//    @RequestMapping("projectExcel/personal")
//    Result personal(HttpServletResponse response, HttpServletRequest request);
//
//    @RequestMapping("projectExcel/personalVolume")
//    Result personalVolume(HttpServletResponse response, HttpServletRequest request);
}
