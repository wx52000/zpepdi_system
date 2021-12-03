package com.zpepdi.eureka_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.Range;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.RangeService;

@RestController
@RequestMapping("range")
public class RangeController {
    private RangeService rangeService;
    @Autowired
    public  void setRangeService(RangeService rangeService){
        this.rangeService = rangeService;
    }

//查询打分范围
    @RequestMapping("query")
    public Result query(){
        return  Result.ok(rangeService.query());
    }

//    修改打分范围
    @RequestMapping("update")
    public  Result update(@RequestBody Range range){
        rangeService.update(range);
        return  Result.ok();
    }
//查询可评价时间
  @RequestMapping("queryDate")
  public Result queryDate(){
    return  Result.ok(rangeService.queryDate());
  }
//  修改评价时间
  @RequestMapping("updateDate")
  public  Result updateDate(@RequestBody Range range){
    rangeService.updateDate(range);
    return  Result.ok();
  }
}
