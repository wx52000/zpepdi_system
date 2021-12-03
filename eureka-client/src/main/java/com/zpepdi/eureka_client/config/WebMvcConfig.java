package com.zpepdi.eureka_client.config;


import com.zpepdi.eureka_client.annotation.resolve.UserIdMethodArgumentResolver;
import com.zpepdi.eureka_client.interceptor.AuthInterceptor;
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


    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userIdMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIdInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/refresh","/down/down360","myLogout");
        super.addInterceptors(registry);
    }

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }



}
