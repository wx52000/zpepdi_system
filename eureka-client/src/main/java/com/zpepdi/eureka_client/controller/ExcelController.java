package com.zpepdi.eureka_client.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.zpepdi.eureka_client.entity.ExcelData;
import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.UserExcel;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.GradeScoreService;
import com.zpepdi.eureka_client.service.GradeTecService;
import com.zpepdi.eureka_client.excel.ExcelDataListener;
import com.zpepdi.eureka_client.excel.ExcelProjectListener;
import com.zpepdi.eureka_client.excel.UserExcelListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
此类根据要求，已不再使用，若后续需要excel导入功能，可启用
 */
@RestController
@RequestMapping("excel")
public class ExcelController {
    private GradeScoreService gradeScoreService;
    private ExcelDataListener excelDataListener;
    private GradeTecService gradeTecService;
    private ExcelProjectListener excelProjectListener;
    private UserExcelListener userExcelListener;
    @Autowired
    public  void  setGradeScoreService(GradeScoreService gradeScoreService){
        this.gradeScoreService = gradeScoreService;
    }

    @Autowired
    public void  setExcelDataListener(ExcelDataListener excelDataListener){
        this.excelDataListener = excelDataListener;
    }
    @Autowired
    public void  setGradeTecService(GradeTecService gradeTecService){
        this.gradeTecService = gradeTecService;
    }
    @Autowired
    public void setExcelProjectListener(ExcelProjectListener excelProjectListener){
        this.excelProjectListener = excelProjectListener;
    }
    @Autowired
    public void setUserExcelListener(UserExcelListener userExcelListener){
      this.userExcelListener = userExcelListener;
    }
//两表导入  ---已经弃用
    @RequestMapping("personalExcel")
    public Result personalExcel(@RequestParam("file") MultipartFile[] files){
        InputStream fileStream = null;
        List<Integer> grade = new ArrayList<>();
        List<Integer> score = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < files.length ;i++ ){
            Sheet sheet = new Sheet(1, 1, ExcelData.class);
            try {
                excelDataListener.clear();
                fileStream = files[i].getInputStream();
                EasyExcelFactory.readBySax(fileStream,sheet,excelDataListener);
                if (files[i].getOriginalFilename().contains("被打分")){
                    score.addAll(excelDataListener.getList());
                }else {
                    grade.addAll(excelDataListener.getList());
                    set.addAll(excelDataListener.getSet());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileStream != null) {
                        fileStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        gradeTecService.addExcel(grade,set);
        gradeScoreService.addExcel(grade,score);

        return Result.ok();
    }
  //表内数据相互  ---已经弃用
    @RequestMapping("amongExcel")
    public Result amongExcel(@RequestParam("file") MultipartFile file){
        InputStream fileStream = null;
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Sheet sheet = new Sheet(1, 1, ExcelData.class);

        try {
            excelDataListener.clear();
            fileStream = file.getInputStream();
            EasyExcelFactory.readBySax(fileStream,sheet,excelDataListener);
            list.addAll(excelDataListener.getList());
            set.addAll(excelDataListener.getSet());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        gradeTecService.addExcel(list,set);
        gradeScoreService.amongExcel(list);
        return Result.ok();
    }

  @RequestMapping("userExcel")
  public Result userExcel(@RequestParam("file") MultipartFile file){
    InputStream fileStream = null;
    Sheet sheet = new Sheet(1, 2, UserExcel.class);
    try {
      fileStream = file.getInputStream();
      EasyExcelFactory.readBySax(fileStream,sheet,userExcelListener);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fileStream != null) {
          fileStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return Result.ok();
  }

    @RequestMapping("project")
    public Result project(@RequestParam("file") MultipartFile file){
        InputStream fileStream = null;
        Sheet sheet = new Sheet(1, 3, ExcelProject.class);
        try {
            fileStream = file.getInputStream();
            EasyExcelFactory.readBySax(fileStream,sheet,excelProjectListener);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.ok(excelProjectListener.getMaps());
    }
}
