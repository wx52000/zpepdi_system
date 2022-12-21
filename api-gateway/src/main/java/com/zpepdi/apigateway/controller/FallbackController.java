package com.zpepdi.apigateway.controller;


import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.zpepdi.apigateway.result.Result;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallbackController {

    @RequestMapping("/defaultfallback")
    public Result defaultfallback(ServerWebExchange exchange){
        Result result = null;
        Exception exception = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
        ServerWebExchange delegate = ((ServerWebExchangeDecorator) exchange).getDelegate();
        System.out.println("接口调用失败，URL="+ delegate.getRequest().getURI() + "error:" + exception);
        if (exception instanceof HystrixTimeoutException) {
            result = new Result(440,"接口调用超时");
        } else if (exception != null && exception.getMessage() != null) {
            result = new Result(440,"接口调用失败: " + exception.getMessage());
        } else {
            result = new Result(440,"接口调用失败");
        }
        return result;
    }
}
