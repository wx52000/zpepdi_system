package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.TecRatioService;
import org.springframework.stereotype.Component;

@Component
public class TecRatioFallbackService implements TecRatioService {
    @Override
    public Result queryByTec(Integer id) {
        return Result.build(440);
    }
}
