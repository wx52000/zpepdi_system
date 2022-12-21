package zpepdi.system.annotation.resolve;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import zpepdi.system.annotation.UserId;

public class UserIdMethodArgumentResolver implements HandlerMethodArgumentResolver {

//    判断是否包含userId 注解
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class)
                && parameter.hasParameterAnnotation(UserId.class);
    }

//    赋予参数userId
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
    }
}
