package com.zpepdi.feign_service.fallback;
import com.zpepdi.feign_service.entity.Range;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.RangeService;
import org.springframework.stereotype.Component;

@Component
public class RangeFallbackService implements RangeService {
    @Override
    public Result query() {
        return Result.build(440);
    }

    @Override
    public Result update(Range range) {
        return Result.build(440);
    }

    @Override
    public Result queryDate() {
        return Result.build(440);
    }

    @Override
    public Result updateDate(Range range) {
        return Result.build(440);
    }
}
