package com.zpepdi.feign_service.controller;


import com.zpepdi.feign_service.entity.Position;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("position")
public class PositionController {
  private PositionService positionService;
  @Autowired
  private void setPositionService(@Qualifier("com.zpepdi.feign_service.service.PositionService") PositionService positionService){
    this.positionService = positionService;
  }

  @RequestMapping("queryByWeight")
  public Result queryByWeight(@RequestHeader Integer id){
    return positionService.queryByWeight(id);
  }

  @RequestMapping("query")
  public Result query(){
    return positionService.query();
  }


  @RequestMapping("update")
  public Result update(@RequestBody List<Position> position){
    return positionService.update(position);
  }
}
