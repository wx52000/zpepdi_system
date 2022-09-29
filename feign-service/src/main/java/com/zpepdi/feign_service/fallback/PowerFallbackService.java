package com.zpepdi.feign_service.fallback;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.PowerService;
import org.springframework.stereotype.Component;

@Component
public class PowerFallbackService implements PowerService {
    @Override
    public Result query() {
        return Result.build(440);
    }
}
