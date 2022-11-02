package com.zpepdi.eureka_client.controller;


import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.dao.appraise.DataTransmissionDao;
import com.zpepdi.eureka_client.service.DataTransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.Volume;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.VolumeService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("volume")
public class VolumeController {
    private VolumeService volumeService;
    @Autowired
    private DataTransmissionService dataTransmissionService;
    @Autowired
    public void  setVolumeService(VolumeService volumeService){
        this.volumeService = volumeService;
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader Integer id){
        return Result.ok(volumeService.queryById(id));
    }

    @RequestMapping("queryByProjectId")
    public Result queryByProjectId(@UserId Integer userId,@RequestBody Project project){
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
    public Result setWorkday(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return volumeService.setWorkday(userId,map);
    }

//提前发放工时和卷册公式比例修改
    @RequestMapping("setWorkdayHigh")
    public Result setWorkdayHigh(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return volumeService.setWorkdayHigh(userId,map);
    }

    @RequestMapping("setWorkdayAdvance")
    public Result setWorkdayAdvance(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return volumeService.setWorkdayAdvance(userId,map);
    }

    @RequestMapping("queryVolumeWorkday")
    public Result queryVolumeWorkday(@RequestBody Map<String,Object> map){
        return volumeService.queryVolumeWorkday(map);
    }
        //卷册提前发放工时所需信息
    @RequestMapping("queryVolumeWorkdayHigh")
    public Result queryVolumeWorkdayHigh(@RequestBody Map<String,Object> map){
        return volumeService.queryVolumeWorkdayHigh(map);
    }

    @RequestMapping("queryVolumeWorkdayLog")
    public Result queryVolumeWorkdayLog(@RequestBody Map<String,String> map){
        return volumeService.queryVolumeWorkdayLog(map);
    }

    @RequestMapping("queryBackupWorkdayLog")
    public Result queryBackupWorkdayLog(@RequestBody Map<String,String> map){
        return volumeService.queryBackupWorkdayLog(map);
    }

    @RequestMapping("queryPlannedPublic")
    public Result queryPlannedPublic(@RequestHeader String queryMonth){
        return volumeService.queryPlannedPublic(queryMonth);
    }

    @RequestMapping("tecProgress")
    public Result tecProgress(@RequestBody Map<String,Object> map){
        return volumeService.tecProgress(map);
    }

    @RequestMapping("manageTecProgress")
    public Result manageTecProgress(@UserId Integer userId,@RequestBody Map<String,Object> map){
        return volumeService.manageTecProgress(userId,map);
    }

    @RequestMapping("tecVolumeCompleteByDate")
    public Result tecVolumeCompleteByDate(@RequestBody Map<String,Object> map){
        return volumeService.tecVolumeCompleteByDate(map);
    }

    @RequestMapping("tecVolumePlanCompleteByDate")
    public Result tecVolumePlanCompleteByDate(@RequestBody Map<String,Object> map){
        return volumeService.tecVolumePlanCompleteByDate(map);
    }

    @RequestMapping("tecVolumeInCompleteByDate")
    public Result tecVolumeInCompleteByDate(@RequestBody Map<String,Object> map){
        return volumeService.tecVolumeInCompleteByDate(map);
    }


    @RequestMapping("setPlanDate")
    public Result setPlanDate(@RequestParam MultipartFile file){
        return volumeService.setPlanDate(file);
    }



    @RequestMapping("queryTodayEntryPlan")
    public Result queryTodayEntryPlan(){
        return volumeService.queryTodayEntryPlan();
    }

    @RequestMapping("queryPlannedPublicDate")
    public Result queryPlannedPublicDate(@RequestHeader String search){
        return volumeService.queryPlannedPublicDate(search);
    }

    @RequestMapping("setSinglePlanDate")
    public Result setSinglePlanDate(@RequestBody Map<String,Object> map){
        return volumeService.setSinglePlanDate(map);
    }

    @RequestMapping("queryRecently10Day")
    public Result queryRecently10Day(@UserId Integer id){
        return volumeService.queryRecently10Day(id);
    }

    @RequestMapping("queryCheckerList")
    public Result queryCheckerList(@RequestHeader("processInstanceId") String processInstanceId){
        return dataTransmissionService.queryCheckerList(processInstanceId);
    }

}
