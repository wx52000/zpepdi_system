package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.Volume;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.VolumeService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VolumeFallbackService implements VolumeService {
    @Override
    public Result queryById(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryByProjectId(Project project) {
        return Result.build(440);
    }

    @Override
    public Result upd(Volume volume) {
        return Result.build(440);
    }

    @Override
    public Result add(Volume volume) {
        return Result.build(440);
    }

    @Override
    public Result query(Map<String, String> params) {
        return Result.build(440);
    }

    @Override
    public Result queryByNumber(Volume volume) {
        return Result.build(440);
    }

    @Override
    public Result setReason(Map<String, String> map) {
        return Result.build(440);
    }

    @Override
    public Result setWorkday(Integer userId, Map<String, String> map) {
        return Result.build(440);
    }

    @Override
    public Result setWorkdayHigh(Integer userId, Map<String, String> map) {
        return Result.build(440);
    }

    @Override
    public Result queryVolumeWorkday(Map<String, String> map) {
        return Result.build(440);
    }
}
