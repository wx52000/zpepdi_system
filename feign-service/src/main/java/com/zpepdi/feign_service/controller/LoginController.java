package com.zpepdi.feign_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.zpepdi.feign_service.entity.User;
import com.zpepdi.feign_service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUrl;

    @Autowired()
    private TokenStore tokenStore;


    @RequestMapping("login")
    public OAuth2AccessToken login(@RequestBody User user){
        ResourceOwnerPasswordResourceDetails resourceDetails =
                new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(user.getUsername());
        resourceDetails.setPassword(user.getPaw());
        resourceDetails.setAccessTokenUri(accessTokenUrl);
        resourceDetails.setClientId(oAuth2ClientProperties.getClientId());
        resourceDetails.setClientSecret(oAuth2ClientProperties.getClientSecret());
        resourceDetails.setGrantType("password");
//        用于请求授权服务器
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
//        表示是密码模式
        restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        return restTemplate.getAccessToken();
    }

    @RequestMapping("refresh")
    public Object refresh(@RequestHeader("refresh_token")String refreshToken){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(oAuth2ClientProperties.getClientId(),oAuth2ClientProperties.getClientSecret());
        String url = accessTokenUrl + "?grant_type=refresh_token&refresh_token="+refreshToken;
        HttpEntity<HashMap<String,Object>> request = new HttpEntity<>(headers);
        return restTemplate.postForObject(url,request, JSONObject.class);
    }
}
