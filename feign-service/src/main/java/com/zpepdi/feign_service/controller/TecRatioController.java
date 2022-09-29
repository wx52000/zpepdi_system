package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.TecRatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tecRatio")
public class TecRatioController {
  private TecRatioService tecRatioService;
  @Autowired
  public  void setTecRatioService(@Qualifier("com.zpepdi.feign_service.service.TecRatioService") TecRatioService tecRatioService){
    this.tecRatioService = tecRatioService;
  }

  @RequestMapping("queryByTec")
  public Result queryByTec(@RequestHeader Integer id){
    return tecRatioService.queryByTec(id);
  }
}
