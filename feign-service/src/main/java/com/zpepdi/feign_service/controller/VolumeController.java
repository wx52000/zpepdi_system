package com.zpepdi.feign_service.controller;

import com.zpepdi.feign_service.annotation.UserId;
import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.Volume;
import com.zpepdi.feign_service.result.Result;
import com.zpepdi.feign_service.service.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("volume")
public class VolumeController {
    private VolumeService volumeService;
    @Autowired
    public void  setVolumeService(@Qualifier("com.zpepdi.feign_service.service.VolumeService") VolumeService volumeService){
        this.volumeService = volumeService;
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader Integer id){
        return volumeService.queryById(id);
    }

    @RequestMapping("queryByProjectId")
    public Result queryByProjectId(@RequestBody Project project){
      return volumeService.queryByProjectId(project);
    }

    @RequestMapping("upd")
    public Result upd(@RequestBody Volume volume){
        return volumeService.upd(volume);
    }

    @RequestMapping("add")
    public Result add(@RequestBody Volume volume){
        return volumeService.add(volume);
    }

    @RequestMapping("query")
    public Result query(@RequestBody Map<String,String> params){
      return volumeService.query(params);
    }

    @RequestMapping("queryByNumber")
    public Result queryByNumber(@RequestBody Volume volume){
        return volumeService.queryByNumber(volume);
    }

    @RequestMapping("setReason")
    public Result setReason(@RequestBody Map<String,String> map){
        return volumeService.setReason(map);
    }

    @RequestMapping("setWorkday")
    public Result setWorkday(@UserId Integer userId, @RequestBody Map<String,String> map){
        return volumeService.setWorkday(userId,map);
    }

    @RequestMapping("setWorkdayHigh")
    public Result setWorkdayHigh(@UserId Integer userId,@RequestBody Map<String,String> map){
        return volumeService.setWorkdayHigh(userId,map);
    }

    @RequestMapping("queryVolumeWorkday")
    public Result queryVolumeWorkday(@RequestBody Map<String,String> map){
        return volumeService.queryVolumeWorkday(map);
    }

}
