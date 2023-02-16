package com.zpepdi.qj_heating.controller;



import com.zpepdi.qj_heating.entity.UserUnit;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.UserUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("userunit")
@CrossOrigin(origins = "*")
public class UserUnitController {

    private UserUnitService userUnitService;
    @Autowired
    public  void  setUserService(UserUnitService userUnitService){
        this.userUnitService = userUnitService;
    }

    @RequestMapping("queryuserconfig")
    public Result queryuserconfig(@RequestBody Map<String,String> map){
        Result result = userUnitService.queryUserUnitconfig(map.get("username"));
        return result;
    }

    @RequestMapping("saveuserconfig")
    public Result saveuserconfig(@RequestBody UserUnit userUnit){
        Object data = userUnitService.queryUserUnitconfig(userUnit.getUsername()).getData();
        Result result;
        if(StringUtils.isEmpty(data)){
            result = userUnitService.adduserconfig(userUnit);
        }else {
            result = userUnitService.upuserconfig(userUnit);
        }
        return result;
    }
}
