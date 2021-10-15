package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.entity.Activity;
import com.zpepdi.feign_service.entity.PrincipalWorkday;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.entity.VirtualDesigner;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ActivityService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ActivityFallbackService implements ActivityService {
    @Override
    public Result setProject(Activity activity) {
        return Result.build(440);
    }

    @Override
    public Result query() {
        return Result.build(440);
    }

    @Override
    public Result queryByUser(Integer userId) {
        return Result.build(400);
    }

    @Override
    public Result queryById(Integer id) {
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
