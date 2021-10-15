package com.zpepdi.feign_service.fallback;
import com.zpepdi.feign_service.entity.ScoreManage;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ScoreManageService;
import org.springframework.stereotype.Component;

@Component
public class ScoreManageFallbackService implements ScoreManageService {
    @Override
    public Result handle(ScoreManage scoreManage) {
        return Result.build(440);
    }
}
