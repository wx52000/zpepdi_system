package com.zpepdi.feign_service.fallback;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProjectWorkDayService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Component
public class ProjectWorkdayFallbackService implements ProjectWorkDayService {
    @Override
    public Result drawLine(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryUsedTecWorkDay(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryWorkDay(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryProjectWorkDay(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryMajorWorkDay(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryBackupWorkDay(Map<String, String> map) {
        return Result.build(440);
    }

    @Override
    public Result queryTecWorkDay(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryTecVolumeRatio(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result setTecVolumeRatio(List<Map<String,Object>> list) {
        return Result.build(440);
    }

    @Override
    public Result queryReserveWorkDay(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result setProWorkDay(Map map) {
        return Result.build(440);
    }

    @Override
    public Result setTecWorkDay(Map map) {
        return Result.build(440);
    }

    @Override
    public Result setBackupWorkDay(Map map) {
        return Result.build(440);
    }

    @Override
    public Result setUserWorkDay(Map map) {
        return Result.build(440);
    }
}
