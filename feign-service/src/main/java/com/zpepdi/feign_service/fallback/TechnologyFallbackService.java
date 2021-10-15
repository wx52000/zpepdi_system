package com.zpepdi.feign_service.fallback;
import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.Technology;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.TechnologyService;
import org.springframework.stereotype.Component;

@Component
public class TechnologyFallbackService implements TechnologyService {
    @Override
    public Result query(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryNotUser() {
        return Result.build(440);
    }

    @Override
    public Result evaluate(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result add(Technology technology) {
        return Result.build(440);
    }

    @Override
    public Result del(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryToSelected() {
        return Result.build(440);
    }

    @Override
    public Result queryById(Project project) {
        return Result.build(440);
    }
}
