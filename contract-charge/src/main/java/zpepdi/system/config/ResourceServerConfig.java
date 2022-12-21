package zpepdi.system.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    @Value("${security.oauth2.resource.id}")
    private String resourceId;



    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("dev");
        accessTokenConverter.setVerifierKey("dev");
        return accessTokenConverter;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    //    @Bean
//    public TokenStore redisTokenStore (){
//        return new RedisTokenStore(redisConnectionFactory);
//    }

//    @Bean
//    public RemoteTokenServices tokenService() {
//        RemoteTokenServices tokenService = new RemoteTokenServices();
//        tokenService.setClientId(clientId);
//        tokenService.setClientSecret(secret);
//        tokenService.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
//        return tokenService;
//    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId);
        resources.tokenStore(jwtTokenStore());
//        resources.authenticationEntryPoint(new MyAuthenticationEntryPoint());
//        resources.tokenServices(tokenService());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/error").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .cors()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                .and().csrf().disable();
    }
}
