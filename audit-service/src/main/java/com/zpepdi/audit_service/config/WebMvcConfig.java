package com.zpepdi.audit_service.config;


import com.zpepdi.audit_service.annotation.resolve.UserMethodArgumentResolver;
import com.zpepdi.audit_service.interceptor.AuthInterceptor;
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
    public UserMethodArgumentResolver userMethodArgumentResolver(){
        return new UserMethodArgumentResolver();
    }


    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIdInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/refresh","/down/down360","/myLogout",
                        "/error","/test","/produceDataTransmit"," /druid/*");
        super.addInterceptors(registry);
    }

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }



}
