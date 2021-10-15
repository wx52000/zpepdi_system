package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProjectTecService;
import org.springframework.stereotype.Component;

@Component
public class ProjectTecFallbackService implements ProjectTecService {
    @Override
    public Result query(Integer id) {
        return Result.build(440);
    }
}
