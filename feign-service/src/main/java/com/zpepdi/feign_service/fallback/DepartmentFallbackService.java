package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.entity.Department;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.DepartmentService;
import org.springframework.stereotype.Component;

@Component
public class DepartmentFallbackService implements DepartmentService {
    @Override
    public Result query() {
        return Result.build(440);
    }

    @Override
    public Result queryNotUser() {
        return Result.build(440);
    }

    @Override
    public Result add(Department department) {
        return Result.build(440);
    }

    @Override
    public Result del(Integer id) {
        return Result.build(440);
    }
}
