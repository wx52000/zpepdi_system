package com.zpepdi.feign_service.service;

import com.zpepdi.feign_service.entity.Project;
import com.zpepdi.feign_service.entity.Volume;
import com.zpepdi.feign_service.fallback.DepartmentFallbackService;
import com.zpepdi.feign_service.fallback.VolumeFallbackService;
import com.zpepdi.feign_service.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "EUREKA-CLIENT", fallback = VolumeFallbackService.class)
public interface VolumeService {

    @RequestMapping("volume/queryById")
    Result queryById(@RequestHeader("id") Integer id);

    @RequestMapping("volume/queryByProjectId")
    Result queryByProjectId(@RequestBody Project project);

    @RequestMapping("volume/upd")
    Result upd(@RequestBody Volume volume);

    @RequestMapping("volume/add")
    Result add(@RequestBody Volume volume);

    @RequestMapping("volume/query")
    Result query(@RequestBody Map<String,String> params);

    @RequestMapping("volume/queryByNumber")
    Result queryByNumber(@RequestBody Volume volume);

    @RequestMapping("volume/setReason")
    Result setReason(@RequestBody Map<String,String> map);

    @RequestMapping("volume/setWorkday")
    Result setWorkday(@RequestHeader("userId") Integer userId,@RequestBody Map<String,String> map);

    @RequestMapping("volume/setWorkdayHigh")
    Result setWorkdayHigh(@RequestHeader("userId") Integer userId,@RequestBody Map<String,String> map);

    @RequestMapping("volume/queryVolumeWorkday")
    Result queryVolumeWorkday(@RequestBody Map<String,String> map);
}
