package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.UserPositionService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserPositionFallbackService implements UserPositionService {
    @Override
    public Result add(Map map) {
        return Result.build(440);
    }

    @Override
    public Result del(Integer id) {
        return Result.build(440);
    }
}
