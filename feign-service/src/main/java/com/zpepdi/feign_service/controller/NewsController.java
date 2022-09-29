package com.zpepdi.feign_service.controller;


import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class NewsController {
  private NewsService newsService;
    @Autowired
  public void setNewsService(@Qualifier("com.zpepdi.feign_service.service.NewsService") NewsService newsService){
    this.newsService = newsService;
    }

    @RequestMapping("count")
  public Result count(@RequestHeader Integer id){
    return newsService.count(id);
    }

  @RequestMapping("query")
  public Result query(@RequestHeader Integer id){
    return newsService.query(id);
  }
}
