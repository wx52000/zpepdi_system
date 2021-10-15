package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ExcelService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelFallbackService implements ExcelService {
    @Override
    public Result userExcel(MultipartFile file) {
        return Result.build(440);
    }

    @Override
    public Result project(MultipartFile file) {
        return Result.build(440);
    }
}
