package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reason")
public class ReasonController {
  private ReasonService reasonService;
  @Autowired
  private void setReasonService(@Qualifier("com.zpepdi.feign_service.service.ReasonService") ReasonService reasonService){
    this.reasonService = reasonService;
  }

  @RequestMapping("queryByType")
  public Result queryByType(@RequestHeader Integer type){
    return reasonService.queryByType(type);
  }
}
