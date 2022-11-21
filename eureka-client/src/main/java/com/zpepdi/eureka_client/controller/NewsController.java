package com.zpepdi.eureka_client.controller;


import com.zpepdi.eureka_client.annotation.UserId;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.NewsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("news")
public class NewsController {
  private NewsService newsService;

    @Autowired
    public void setNewsService(NewsService newsService){
    this.newsService = newsService;
    }

    @RequestMapping("count")
    public Result count(@UserId Integer id){
    return newsService.newsCount(id);
    }

  @RequestMapping("queryCountByType")
  public Result queryCountByType(@UserId Integer id){
    return newsService.queryCountByType(id);
  }

    @RequestMapping("queryByType")
    public Result queryByType(@UserId Integer id,@RequestHeader Integer type){
      return newsService.queryByType(id,type);
    }

    @RequestMapping("query")
    public Result query(@UserId Integer id){
      return newsService.query(id);
    }

    @RequestMapping("queryLog")
    public Result query(@UserId Integer id, @RequestHeader("index")Integer index){
      return newsService.queryLog(id,index);
    }

    @RequestMapping("check")
    public Result query(@UserId Integer id, @RequestBody List<Map<String,Object>> list,@RequestHeader Integer check){
        return newsService.check(id,list,check);
    }

    @RequestMapping("querySelfCheck")
    public Result querySelfCheck(@UserId Integer id){
      return newsService.querySelfCheck(id);
    }

    @RequestMapping("queryExtend")
    public Result queryExtend(@UserId Integer userId, @RequestBody Map<String,Object> map){
      return newsService.queryExtend(userId,map);
    }
    @RequestMapping("withdrawOverAll")
    public Result withdrawOverAll(@UserId Integer id, @RequestBody Map<String,Object> map){
      return newsService.withdrawOverAll(map);
    }








}
