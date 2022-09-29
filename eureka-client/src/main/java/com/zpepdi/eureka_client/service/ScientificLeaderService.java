package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ScientificLeaderService {

    Result setLeader(Integer userId, Map<String,Object> map);

    Result setLeaderChecker(Integer userId,Map<String,Object> map);

    Result queryLeader(Integer id);

    Result querySurplus(Integer id);

    Result delLeader(Integer id);

    Result addTermByGeneral(Integer userId, Map<String,Object> map);

    Result addTermByLeader(Integer userId, Map<String,Object> map);

    Result setToCheck(Integer id);

    Result uploadFile(Integer userId, MultipartFile file, Map<String, Object> map);

    Result queryTerm(Integer userId, Integer projectId);


    Result queryFilesByTerm(Integer id);

    HttpServletResponse downFiles(Integer id, HttpServletResponse response);
}
