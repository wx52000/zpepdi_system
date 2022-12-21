package zpepdi.system.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import zpepdi.system.tools.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
//        System.out.println(request.getRequestURI());
        String authorization = request.getHeader("Authorization");
        Map<String,Integer> map = JwtUtils.getUserId(authorization);
        request.setAttribute("userId" , map.get("userId"));
        request.setAttribute("roleId", map.get("roleId"));
        return true;
    }

}
