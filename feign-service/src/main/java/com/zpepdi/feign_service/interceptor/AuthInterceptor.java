package com.zpepdi.feign_service.interceptor;

import com.zpepdi.feign_service.tools.JwtUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        System.out.println(request.getRequestURI());
        String authorization = request.getHeader("Authorization");
        Integer userId = JwtUtils.getUserId(authorization);
        request.setAttribute("userId" , userId);
        return true;
    }

}
