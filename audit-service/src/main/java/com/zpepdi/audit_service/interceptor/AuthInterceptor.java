package com.zpepdi.audit_service.interceptor;

import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.tools.JwtUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        User user = JwtUtils.getUser(authorization);
        request.setAttribute("user" , user);
        return true;
    }

}
