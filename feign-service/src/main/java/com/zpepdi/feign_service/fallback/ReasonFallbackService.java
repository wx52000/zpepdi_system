package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ReasonService;
import org.springframework.stereotype.Component;

@Component
public class ReasonFallbackService implements ReasonService {
    @Override
    public Result queryByType(Integer type) {
        return Result.build(440);
    }
}
