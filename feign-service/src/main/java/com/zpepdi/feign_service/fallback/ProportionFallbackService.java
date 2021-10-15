package com.zpepdi.feign_service.fallback;
import com.zpepdi.feign_service.entity.Proportion;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProportionService;
import org.springframework.stereotype.Component;

@Component
public class ProportionFallbackService implements ProportionService {
    @Override
    public Result add(Proportion proportion) {
        return Result.build(440);
    }

    @Override
    public Result queryLastTime(Proportion proportion) {
        return Result.build(440);
    }

    @Override
    public Result queryLastTwoTime(Proportion proportion) {
        return Result.build(440);
    }
}
