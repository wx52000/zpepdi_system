package com.zpepdi.eureka_client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.NewsService;

@RestController
@RequestMapping("news")
public class NewsController {
  private NewsService newsService;
    @Autowired
  public void setNewsService(NewsService newsService){
    this.newsService = newsService;
    }

    @Value("${server.port}")
    private volatile String port;

    @RequestMapping("count")
  public Result count(@RequestHeader Integer id){
    return newsService.newsCount(id);
    }

  @RequestMapping("query")
  public Result query(@RequestHeader Integer id){
    return newsService.query(id);
  }

  @RequestMapping("test")
  public Result test(){
    return Result.ok(port);
  }
}
