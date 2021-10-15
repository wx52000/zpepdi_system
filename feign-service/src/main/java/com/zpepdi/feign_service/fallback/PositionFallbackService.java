package com.zpepdi.feign_service.fallback;
import com.zpepdi.feign_service.entity.Position;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.PositionService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositionFallbackService implements PositionService {
    @Override
    public Result queryByWeight(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result query() {
        return Result.build(440);
    }

    @Override
    public Result update(List<Position> position) {
        return Result.build(440);
    }
}
