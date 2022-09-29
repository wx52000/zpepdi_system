package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.entity.PartParam;
import com.zpepdi.feign_service.entity.TecScore;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.TecScoreService;
import com.zpepdi.feign_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("tecScore")
public class TecScoreController {

    private TecScoreService tecScoreService;

    @Autowired
    public void setTecScoreService(@Qualifier("com.zpepdi.feign_service.service.TecScoreService") TecScoreService tecScoreService){
        this.tecScoreService = tecScoreService;
    }

    @RequestMapping("queryByGradeId")
    public Result queryByGradeId(@RequestHeader Integer id){
        return tecScoreService.queryByGradeId(id);
    }

    @RequestMapping("queryByScoreId")
    public Result queryByScoreId(@RequestBody User user){
      return tecScoreService.queryByScoreId(user);
    }

    @RequestMapping("appraise")
    public Result appraise(@UserId Integer userId, @RequestBody TecScore tecScore){
        return tecScoreService.appraise(userId,tecScore);
    }

    @RequestMapping("queryScore")
    public Result queryScore(@RequestBody User user){
        return tecScoreService.queryScore(user);
    }

    @RequestMapping("query")
    public Result query(@RequestBody User user){
        return tecScoreService.query(user);

    }

    @RequestMapping("detail")
    public Result detail(HttpServletResponse response,HttpServletRequest request){
      return  Result.ok();
    }

  @RequestMapping("part")
  public Result part(@RequestBody PartParam partParam){
        return tecScoreService.part(partParam);
  }


  @RequestMapping("excel")
    public Result excel(HttpServletRequest request , HttpServletResponse response) throws IOException {
      return  Result.ok();
    }
}
