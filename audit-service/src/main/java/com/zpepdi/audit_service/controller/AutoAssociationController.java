package com.zpepdi.audit_service.controller;


import com.zpepdi.audit_service.annotation.RequestUser;
import com.zpepdi.audit_service.entity.User;
import com.zpepdi.audit_service.result.Result;
import com.zpepdi.audit_service.service.AutoAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autoAssociation")
public class AutoAssociationController {

    @Autowired
    private AutoAssociationService autoAssociationService;

    @RequestMapping("queryBySelf")
    public Result queryBySelf(@RequestUser User user){
        return autoAssociationService.queryBySelf(user);
    }
}
