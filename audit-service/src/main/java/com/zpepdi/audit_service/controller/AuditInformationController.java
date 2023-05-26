package com.zpepdi.audit_service.controller;

import com.zpepdi.audit_service.annotation.RequestUser;
import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;
import com.zpepdi.audit_service.service.AuditInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auditInformation")
public class AuditInformationController {

    @Autowired
    private AuditInformationService auditInformationService;

    @RequestMapping("queryCount")
    public Result queryCount(@RequestUser User user){
        return auditInformationService.queryCount(user);
    }
    @RequestMapping("addAuditInformation")
    public Result addAuditInformation(@RequestUser User user, @RequestBody Map<String,Object> map){
        return auditInformationService.addAuditInformation(user,map);
    }

    @RequestMapping("renew")
    public Result renew(@RequestUser User user, @RequestBody Map<String,Object> map){
        return auditInformationService.renew(user,map);
    }



    @RequestMapping("queryBySelf")
    public Result queryBySelf(@RequestUser User user, @RequestBody Map<String,Object> map){
        return auditInformationService.queryBySelf(user,map);
    }

    @RequestMapping("submitAdult")
    public Result submitAdult(@RequestUser User user, @RequestBody Map<String,Object> map){
        return auditInformationService.submitAdult(user,map);
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestUser User user, @RequestHeader("id") Integer id){
        return auditInformationService.queryById(id);
    }

    @RequestMapping("delAuditInformation")
    public Result delAuditInformation(@RequestHeader("id")Integer id){
        return auditInformationService.delAuditInformation(id);
    }

    @RequestMapping("setIsSeen")
    public Result setIsSeen(@RequestHeader("id")Integer id){
        return auditInformationService.setIsSeen(id);
    }

}
