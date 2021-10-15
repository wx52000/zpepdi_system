package com.zpepdi.feign_service.fallback;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProjectExcelService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProjectExcelFallbackService implements ProjectExcelService {
//    @Override
//    public Result statisticAll(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }
//
//    @Override
//    public Result statistic(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }
//
//    @Override
//    public Result everyoneAll(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }
//
//    @Override
//    public Result everyone(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }
//
//    @Override
//    public Result volumeAll(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }
//
//    @Override
//    public Result volume(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }
//
//    @Override
//    public Result personal(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }
//
//    @Override
//    public Result personalVolume(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }
}
