package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.entity.Range;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("range")
public class RangeController {
    private RangeService rangeService;
    @Autowired
    public  void setRangeService(@Qualifier("com.zpepdi.feign_service.service.RangeService") RangeService rangeService){
        this.rangeService = rangeService;
    }

    @RequestMapping("query")
    public Result query(){
        return  rangeService.query();
    }

    @RequestMapping("update")
    public  Result update(@RequestBody Range range){
        return rangeService.update(range);
    }

  @RequestMapping("queryDate")
  public Result queryDate(){
    return rangeService.queryDate();
  }

  @RequestMapping("updateDate")
  public  Result updateDate(@RequestBody Range range){
    return rangeService.updateDate(range);
  }
}
