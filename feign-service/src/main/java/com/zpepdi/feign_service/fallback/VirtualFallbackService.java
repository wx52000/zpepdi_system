package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.entity.PrincipalWorkday;
import com.zpepdi.feign_service.entity.Virtual;
import com.zpepdi.feign_service.entity.VirtualDesigner;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.VirtualService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Component
public class VirtualFallbackService implements VirtualService {
    @Override
    public Result setProject(Virtual virtual) {
        return Result.build(440);
    }

    @Override
    public Result query() {
        return Result.build(440);
    }

    @Override
    public Result queryByUser(Integer id) {
        return Result.build(400);
    }

    @Override
    public Result queryById(Map<String,Object> map) {
        return Result.build(440);
    }

    @Override
    public Result queryUsedWorkday(Map<String, Object> map, Integer userId) {
        return Result.build(440);
    }

    @Override
    public Result queryWorkday(Map<String, Object> map, Integer id) {
        return Result.build(440);
    }

    @Override
    public Result setWorkday(Map<String, Object> map, Integer userId) {
        return Result.build(440);
    }

}
