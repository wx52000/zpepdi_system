package com.zpepdi.feign_service.fallback;


import com.zpepdi.feign_service.entity.GradeScore;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.GradeScoreService;
import org.springframework.stereotype.Component;

@Component
public class GradeScoreFallbackService implements GradeScoreService {
    @Override
    public Result manage(GradeScore gradeScore) {
        return Result.build(440);
    }

    @Override
    public Result queryTec(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryDep(Integer id) {
        return Result.build(440);
    }
}
