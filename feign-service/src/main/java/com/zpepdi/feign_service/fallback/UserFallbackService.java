package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.UserService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class UserFallbackService implements UserService {

    @Override
    public Result add(User user) {
        return Result.build(440);
    }

    @Override
    public Result del(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result upd(Map<String,Object> user) {
        return Result.build(440);
    }

    @Override
    public Result pwdReset(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result query(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryById(Integer id) {
        System.out.println(id);
        return Result.build(440);
    }

    @Override
    public Result information(Integer userId) {
        return Result.build(440);
    }

    @Override
    public Result workdayLogById(Integer userId) {
        return Result.build(440);
    }

    @Override
    public Result workdayLog(Map<String, Object> map) {
        return Result.build(440);
    }

    @Override
    public Result workday(String date) {
        return Result.build(440);
    }

    @Override
    public Result queryToupd(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryByTid(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryNotSelf(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryToScore(Integer userId, Map<String, Object> map) {
        return Result.build(440);
    }

    @Override
    public Result queryScoreList(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryNotScore(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryAppriseAll() {
        return Result.build(440);
    }

    @Override
    public Result queryAppraise(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryNotAppraise(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryNotScored(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryNotTecApp(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryByTec(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryByName(User user) {
        return Result.build(440);
    }

    @Override
    public Result paw(Integer userId, User user) {
        return Result.build(400);
    }

    @Override
    public Result userAll(Integer mode) {
        return Result.build(440);
    }

    @Override
    public Result userAllAndState(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result userAllAndGroup(Integer id, Integer mode) {
        return Result.build(440);
    }

//    @Override
//    public Result personExcel(HttpServletRequest request, HttpServletResponse response) {
//        return Result.build(440);
//    }
}
