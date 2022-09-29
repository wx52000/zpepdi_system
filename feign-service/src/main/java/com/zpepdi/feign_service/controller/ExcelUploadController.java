package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("excel")
public class ExcelUploadController {
    @Qualifier("com.zpepdi.feign_service.service.ExcelService")
    @Autowired
    private ExcelService excelDataListener;

    @RequestMapping("userExcel")
    public Result userExcel(@RequestParam("file") MultipartFile file){
        return excelDataListener.userExcel(file);
    }

    @RequestMapping("project")
    public Result project(@RequestParam("file") MultipartFile file){

        return excelDataListener.project(file);
    }
}
