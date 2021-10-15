package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.fallback.ExcelFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(value = "EUREKA-CLIENT", fallback = ExcelFallbackService.class)
public interface ExcelService {
    @RequestMapping(value = "excel/userExcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result userExcel(@RequestPart("file") MultipartFile file);

    @RequestMapping(value = "excel/project" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result project(@RequestPart("file") MultipartFile file);
}
