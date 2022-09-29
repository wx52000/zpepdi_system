package com.zpepdi.feign_service.controller;


import com.zpepdi.feign_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zpepdi.feign_service.result.Result;

@RestController
@RequestMapping("/user")
public class UserFeignController {
    @Autowired
    private UserService userService;


    @RequestMapping("test")
    public String login(){
        return "success";
    }

    @RequestMapping("set")
    public Result set(@RequestParam String s){
        return Result.ok();
    }

//    @RequestMapping("get")
//    public Result get(@RequestParam String s){
//        return Result.ok();
//    }
//
    @GetMapping(value = "get")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object get(Authentication authentication){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        String token = details.getTokenValue();
        return token;
    }
}
