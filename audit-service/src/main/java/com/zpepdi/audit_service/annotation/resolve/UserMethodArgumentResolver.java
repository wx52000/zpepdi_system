package com.zpepdi.audit_service.annotation.resolve;

import com.zpepdi.audit_service.annotation.RequestUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserMethodArgumentResolver implements HandlerMethodArgumentResolver {

//    判断是否包含userId 注解
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(com.zpepdi.audit_service.entity.User.class)
                && parameter.hasParameterAnnotation(RequestUser.class);
    }

//    赋予参数userId
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getAttribute("user", RequestAttributes.SCOPE_REQUEST);
    }
}
