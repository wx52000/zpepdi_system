package com.zpepdi.feign_service.fallback;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.Down360Service;
import feign.Response;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class Down360FallbackService implements Down360Service {
//    @Override
//    public Response down360() {
//        return Response.builder().build();
//    }
//}
