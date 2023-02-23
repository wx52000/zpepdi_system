package com.zpepdi.qj_heating.controller;



import com.zpepdi.qj_heating.entity.UserUnitys;
import com.zpepdi.qj_heating.entity.UserUnitcy;
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

    @RequestMapping("queryuserys")
    public Result queryuserys(@RequestBody Map<String,String> map){
        Result result = userUnitService.queryUserUnitys(map.get("username"));
        return result;
    }

    @RequestMapping("queryusercy")
    public Result queryusercy(@RequestBody Map<String,String> map){
        Result result = userUnitService.queryUserUnitcy(map.get("username"));
        return result;
    }

    @RequestMapping("saveuserys")
    public Result saveuserys(@RequestBody UserUnitys userUnitys){
        Result result = Result.ok("");
        if(!StringUtils.isEmpty(userUnitys.getUserys())){
            return userUnitService.adduserys(userUnitys);
        }
        return result;
    }

    @RequestMapping("deluserys")
    public Result deluserysg(@RequestBody Map<String,String> map){
        Result result = Result.ok("");
        if(!StringUtils.isEmpty(map.get("ysid"))){
            return userUnitService.deluserys(map.get("ysid"));
        }
        return result;

    }

    @RequestMapping("saveusercy")
    public Result saveusercy(@RequestBody UserUnitcy userUnitcy){
        Object data = userUnitService.queryUserUnitcy(userUnitcy.getUsername()).getData();
        Result result;
        if(StringUtils.isEmpty(data)){
            result = userUnitService.addusercy(userUnitcy);
        }else {
            result = userUnitService.upusercy(userUnitcy);
        }
        return result;
    }
}
