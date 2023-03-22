package com.zpepdi.qj_airhammer.config;


import com.zpepdi.qj_airhammer.annotation.resolve.RoleIdMethodArgumentResolver;
import com.zpepdi.qj_airhammer.annotation.resolve.UserIdMethodArgumentResolver;
import com.zpepdi.qj_airhammer.tools.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public AuthInterceptor userIdInterceptor() {
        return new AuthInterceptor();
    }
    @Bean
    public UserIdMethodArgumentResolver userIdMethodArgumentResolver(){
        return new UserIdMethodArgumentResolver();
    }

    @Bean
    public RoleIdMethodArgumentResolver roleIdMethodArgumentResolver(){
        return new RoleIdMethodArgumentResolver();
    }


    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userIdMethodArgumentResolver());
        argumentResolvers.add(roleIdMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIdInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error");
        super.addInterceptors(registry);
    }

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }



}
