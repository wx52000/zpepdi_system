package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.entity.PartParam;
import com.zpepdi.feign_service.entity.TecScore;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.TecScoreService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class TecScoreFallbackService implements TecScoreService {
    @Override
    public Result queryByGradeId(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result queryByScoreId(User user) {
        return Result.build(440);
    }

    @Override
    public Result appraise(Integer userId, TecScore tecScore) {
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

//    @Override
//    public Result detail(HttpServletResponse response, HttpServletRequest request) {
//        return Result.build(440);
//    }

    @Override
    public Result part(PartParam partParam) {
        return Result.build(440);
    }
//
//    @Override
//    public Result excel(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        return Result.build(440);
//    }
}
