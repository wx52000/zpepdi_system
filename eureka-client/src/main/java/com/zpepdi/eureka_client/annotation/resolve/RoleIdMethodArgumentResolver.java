package com.zpepdi.eureka_client.annotation.resolve;

import com.zpepdi.eureka_client.annotation.RoleId;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RoleIdMethodArgumentResolver implements HandlerMethodArgumentResolver {
    //    判断是否包含userId 注解
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class)
                && parameter.hasParameterAnnotation(RoleId.class);
    }

    //    roleId
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getAttribute("roleId", RequestAttributes.SCOPE_REQUEST);
    }
}
