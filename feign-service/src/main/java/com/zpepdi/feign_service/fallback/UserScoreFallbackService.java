package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.entity.PartParam;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.entity.UserScore;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.UserScoreService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class UserScoreFallbackService implements UserScoreService {
    @Override
    public Result queryByGradeId(User user) {
        return Result.build(440);
    }

    @Override
    public Result queryByScoreId(User user) {
        return Result.build(440);
    }

    @Override
    public Result selectByGradeId(User user) {
        return Result.build(440);
    }

    @Override
    public Result appraise(Integer id, UserScore userScore) {
        return Result.build(440);
    }

    @Override
    public Result queryScore(User user) {
        return Result.build(440);
    }

    @Override
    public Result query(User user) {
        return Result.build(440);
    }

    @Override
    public Result add(List<UserScore> list) {
        return Result.build(440);
    }

    @Override
    public Result del(UserScore userScore) {
        return Result.build(440);
    }

    @Override
    public Result part(PartParam partParam) {
        return Result.build(440);
    }

}
