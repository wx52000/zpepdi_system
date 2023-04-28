package com.zpepdi.audit_service.controller;

import com.zpepdi.audit_service.annotation.RequestUser;
import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;
import com.zpepdi.audit_service.service.AuditorInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auditorInformation")
public class AuditorInformationController {
    @Autowired
    private AuditorInformationService auditorInformationService;

    @RequestMapping("addAuditor")
    public Result addAuditor( @RequestBody Map<String,Object> map){
        return auditorInformationService.addAuditor(map);
    }

    @RequestMapping("updateAuditor")
    public Result updateAuditor(@RequestUser User user, @RequestBody Map<String,Object> map){
        return auditorInformationService.updateAuditor(user,map);
    }

    @RequestMapping("queryBySelf")
    public Result queryBySelf(@RequestUser User user,@RequestBody Map<String,Object> map){
        return auditorInformationService.queryBySelf(user,map);
    }

    @RequestMapping("queryBySelfDown")
    public Result queryBySelfDown(@RequestUser User user,@RequestBody Map<String,Object> map){
        return auditorInformationService.queryBySelfDown(user,map);
    }
}
