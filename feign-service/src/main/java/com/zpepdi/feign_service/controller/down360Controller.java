package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.Down360Service;
import com.zpepdi.feign_service.tools.Download;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("down")
public class down360Controller {
    @Qualifier("com.zpepdi.feign_service.service.Down360Service")
    @Autowired
    private Down360Service down360Service;

    @RequestMapping("down360")
    public Result down360(HttpServletResponse response) {
        Response serviceResponse = down360Service.down360();
        Download.downloadFile(serviceResponse,response);
        return Result.ok();
    }
}
