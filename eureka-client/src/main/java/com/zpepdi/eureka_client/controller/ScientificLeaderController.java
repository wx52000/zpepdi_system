package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScientificLeaderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("scientificLeader")
public class ScientificLeaderController {
    @Autowired
    private ScientificLeaderService scientificLeaderService;

    @RequestMapping("setLeader")
    public Result setLeader(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return scientificLeaderService.setLeader(userId,map);
    }

    @RequestMapping("setLeaderChecker")
    public Result setLeaderChecker(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return scientificLeaderService.setLeaderChecker(userId,map);
    }

    @RequestMapping("queryLeader")
    public Result queryLeader(@RequestHeader Integer id) {
        return scientificLeaderService.queryLeader(id);
    }

    @RequestMapping("querySurplus")
    public Result querySurplus(@RequestHeader Integer id) {
        return scientificLeaderService.querySurplus(id);
    }


    @RequestMapping("delLeader")
    public Result delLeader(@RequestHeader Integer id) {
        return scientificLeaderService.delLeader(id);
    }

    @RequestMapping("addTermByGeneral")
    public Result addTermByGeneral(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return scientificLeaderService.addTermByGeneral(userId,map);
    }

    @RequestMapping("addTermByLeader")
    public Result addTermByLeader(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return scientificLeaderService.addTermByLeader(userId,map);
    }

    @RequestMapping("setToCheck")
    public Result setToCheck(@RequestHeader Integer id) {
        return scientificLeaderService.setToCheck(id);
    }

    @RequestMapping("uploadFile")
    public Result uploadFile(@UserId Integer userId,@RequestParam("file")MultipartFile file, @RequestParam("id")Integer id,
    @RequestParam("projectId") Integer projectId) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("projectId", projectId);
        return scientificLeaderService.uploadFile(userId,file,map);
    }

    @RequestMapping("queryTerm")
    public Result queryTerm(@UserId Integer userId, @RequestHeader Integer projectId) {
        return scientificLeaderService.queryTerm(userId,projectId);
    }

    @RequestMapping("queryFilesByTerm")
    public Result queryFilesByTerm(@RequestHeader Integer id) {
        return scientificLeaderService.queryFilesByTerm(id);
    }

    @RequestMapping("downFiles")
    public HttpServletResponse downFiles(@RequestBody Map<String,Object> map,HttpServletResponse response) {
        return scientificLeaderService.downFiles(Integer.valueOf(map.get("id").toString()),response);
    }

}
