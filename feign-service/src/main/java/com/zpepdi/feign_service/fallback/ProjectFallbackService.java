package com.zpepdi.feign_service.fallback;
import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ProjectService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class ProjectFallbackService implements ProjectService {
    @Override
    public Result drawLine(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result add(Project project) {
        return Result.build(440);
    }

    @Override
    public Result addNumber(Project project) {
        return Result.build(440);
    }

    @Override
    public Result upd(Project project) {
        return Result.build(440);
    }

    @Override
    public Result updState(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result spider(Project project) {
        return Result.build(440);
    }

    @Override
    public Result queryById(Integer userId, Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryHumanToBackup(Integer id, Map<String, Object> map) {
        return Result.build(440);
    }

    @Override
    public Result setWorkdayBackup(Integer id, String date, List<Map<String, Object>> list) {
        return Result.build(440);
    }

    @Override
    public Result queryByAdmin(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryByGeneral(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryByPrincipal(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryProByPrincipal(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryByDesigner(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryByChecker(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryByHeadman(User user) {
        return Result.build(440);
    }

//    @Override
//    public Result projectExcel(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }

    @Override
    public Result queryAll() {
        return Result.build(440);
    }

    @Override
    public Result queryPrincipal(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result homepageVolume(Integer id, Map<String,String> map) {
        return Result.build(440);
    }

    @Override
    public Result homepageProject(Integer userId) {
        return Result.build(400);
    }
}
