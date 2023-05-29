package com.zpepdi.qj_airhammer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zpepdi.qj_airhammer.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface FlowService {
    Result compute(Map<String, Object> map);
    Result judge(Map<String, Object> map);

    HttpServletResponse export(HttpServletResponse response, String json) throws IOException;

    HttpServletResponse export1(HttpServletResponse response, String json) throws IOException;
    HttpServletResponse export2(HttpServletResponse response, String json) throws IOException;

    Result save(Integer userId,Map<String, Object> map) throws UnsupportedEncodingException;


    HttpServletResponse upload(HttpServletResponse response,Integer userId,MultipartFile file, HttpServletRequest request) throws Exception;

    Result mpa(Map<String, Object> map);

    Result temperature(Map<String, Object> map);
}
