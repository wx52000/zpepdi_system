package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.NewsService;
import org.springframework.stereotype.Component;

@Component
public class NewsFallbackService implements NewsService {
    @Override
    public Result count(Integer id) {
        return Result.build(440);
    }

    @Override
    public Result query(Integer id) {
        return Result.build(440);
    }
}
