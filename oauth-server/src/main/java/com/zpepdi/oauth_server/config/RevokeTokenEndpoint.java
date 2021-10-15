package com.zpepdi.oauth_server.config;

import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Autowired
    @Qualifier("jwtTokenStore")
    TokenStore tokenStore;

    @RequestMapping( "/logot")
    public String revokeToken(@RequestHeader String access_token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
        // 清空access token
        tokenStore.removeAccessToken(accessToken);
        // 清空 refresh token
        return  "注销成功";
    }

}
