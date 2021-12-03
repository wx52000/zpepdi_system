package com.zpepdi.eureka_client.controller;


import com.zpepdi.eureka_client.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Volume;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.VolumeService;

import java.util.Map;

@RestController
@RequestMapping("volume")
public class VolumeController {
    private VolumeService volumeService;
    @Autowired
    public void  setVolumeService(VolumeService volumeService){
        this.volumeService = volumeService;
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader Integer id){
        return Result.ok(volumeService.queryById(id));
    }

    @RequestMapping("queryByProjectId")
    public Result queryByProjectId(@RequestBody Project project){
      return Result.ok(volumeService.queryByProjectId(project));
    }

    @RequestMapping("upd")
    public Result upd(@RequestBody Volume volume){
        volumeService.upd(volume);
        return Result.ok();
    }

    @RequestMapping("add")
    public Result add(@RequestBody Volume volume){
        volumeService.add(volume);
        return Result.ok();
    }

//    根据人员或卷册信息模糊查询
    @RequestMapping("query")
    public Result query(@RequestBody Map<String,String> params){
        return Result.ok(volumeService.queryVolume(params.get("user"),params.get("volume")));
    }

    @RequestMapping("queryByNumber")
    public Result queryByNumber(@RequestBody Volume volume){
        return volumeService.queryByNumber(volume);
    }

//    卷册未按时完成原因
    @RequestMapping("setReason")
    public Result setReason(@RequestBody Map<String,String> map){
        return volumeService.setReason(map);
    }
//工时设置
    @RequestMapping("setWorkday")
    public Result setWorkday(@UserId Integer userId, @RequestBody Map<String,String> map){
        return volumeService.setWorkday(userId,map);
    }
//提前发放工时和卷册公式比例修改
    @RequestMapping("setWorkdayHigh")
    public Result setWorkdayHigh(@UserId Integer userId,@RequestBody Map<String,String> map){
        return volumeService.setWorkdayHigh(userId,map);
    }
//卷册提前发放工时所需信息
    @RequestMapping("queryVolumeWorkday")
    public Result queryVolumeWorkday(@RequestBody Map<String,String> map){
        return volumeService.queryVolumeWorkday(map);
    }

    @RequestMapping("queryVolumeWorkdayLog")
    public Result queryVolumeWorkdayLog(@RequestBody Map<String,String> map){
        return volumeService.queryVolumeWorkdayLog(map);
    }

    @RequestMapping("queryBackupWorkdayLog")
    public Result queryBackupWorkdayLog(@RequestBody Map<String,String> map){
        return volumeService.queryBackupWorkdayLog(map);
    }
}
