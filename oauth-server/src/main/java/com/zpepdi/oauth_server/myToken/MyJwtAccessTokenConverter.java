package com.zpepdi.oauth_server.myToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyJwtAccessTokenConverter extends DefaultAccessTokenConverter {

    public MyJwtAccessTokenConverter() {
        super.setUserTokenConverter(new MyUserAuthenticationConverter());
    }

    private class MyUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
            //这里添加你的参数
            response.put("userId", ((MyUserDetails) authentication.getPrincipal()).getUserId());
            response.put("username", ((MyUserDetails) authentication.getPrincipal()).getUsername());
            response.put("roleId",((MyUserDetails) authentication.getPrincipal()).getRoleId());
            if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }

            return response;
        }
    }

}
