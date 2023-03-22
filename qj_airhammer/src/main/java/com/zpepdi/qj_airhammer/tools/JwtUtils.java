package com.zpepdi.qj_airhammer.tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {



    public static Map<String,Integer> getUserId(String authorization){
        String token = authorization.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey("dev".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        Map<String,Integer> map = new HashMap<>();
        map.put("userId",Integer.valueOf(claims.get("userId").toString()));
        map.put("roleId",Integer.valueOf(claims.get("roleId").toString()));
        return map;
    }
}
