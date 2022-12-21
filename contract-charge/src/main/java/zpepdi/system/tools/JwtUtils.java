package zpepdi.system.tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    public static Object jwtParser(Authentication authentication) {
        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String jwtToken = details.getTokenValue();
        Claims claims = Jwts.parser()
                .setSigningKey("dev".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims;
    }

    public static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey("dev".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

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
