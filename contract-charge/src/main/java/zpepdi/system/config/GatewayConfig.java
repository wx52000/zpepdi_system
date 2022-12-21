package zpepdi.system.config;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//路由分发
@Configuration
public class GatewayConfig {

    @Bean
    Logger.Level level() {
        return Logger.Level.FATAL;
    }
}
