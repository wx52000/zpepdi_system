package com.zpepdi.eureka_client.controller;

import com.alibaba.fastjson.JSONObject;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.DataTransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
/*
登录接口，转接到oauth 上获取jwt
 */
@RestController
public class LoginController {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    // 从配置文件中获token客户端url
    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUrl;

    @Autowired()
    private TokenStore tokenStore;


    @RequestMapping("login")
    public Result login(@RequestBody User user){
        ResourceOwnerPasswordResourceDetails resourceDetails =
                new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(user.getUsername());
        resourceDetails.setPassword(user.getPaw());
        resourceDetails.setAccessTokenUri(accessTokenUrl);
        resourceDetails.setClientId(oAuth2ClientProperties.getClientId());
        resourceDetails.setClientSecret(oAuth2ClientProperties.getClientSecret());
        //        表示是密码模式
        resourceDetails.setGrantType("password");
        //        用于请求授权服务器
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        try {
            //            异常处理，用于判断密码是否正确
            OAuth2AccessToken token = restTemplate.getAccessToken();
            return Result.ok(token);
        }catch (Exception e){
            e.printStackTrace();;
            return Result.build(550,e.getCause().getMessage());
        }
    }

    @RequestMapping("refresh")
    //token 更新，暂未使用
    public Object refresh(@RequestHeader("refresh_token")String refreshToken){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(oAuth2ClientProperties.getClientId(),oAuth2ClientProperties.getClientSecret());
        String url = accessTokenUrl + "?grant_type=refresh_token&refresh_token="+refreshToken;
        HttpEntity<HashMap<String,Object>> request = new HttpEntity<>(headers);
        return restTemplate.postForObject(url,request, JSONObject.class);
    }

}
