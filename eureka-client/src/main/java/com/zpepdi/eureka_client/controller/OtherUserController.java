package com.zpepdi.eureka_client.controller;

import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.OtherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("otherUser")
public class OtherUserController {
    @Autowired
    private OtherUserService otherUserService;

    @RequestMapping("queryById")
    public Result queryById(@UserId Integer id){
        return  otherUserService.queryById(id);
    }

    @RequestMapping("setPaw")
    public Result setPaw(@UserId Integer id, @RequestBody Map<String,Object> map){
        return  otherUserService.setPaw(id,map);
    }


    @RequestMapping("queryUserByOffice")
    public Result queryUserByOffice(@RequestHeader Integer id){
        return  otherUserService.queryByOffice(id);
    }

    @RequestMapping("queryCheckList")
    public Result queryCheckList(@UserId Integer id){
        return  otherUserService.queryCheckList(id);
    }

    @RequestMapping("setCheck")
    public Result setCheck(@RequestBody Map<String,Object> map){
        return  otherUserService.setCheck(map);
    }
}
