package spring.core.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.core.App;
import spring.core.beans.Client;

import javax.annotation.Resource;

//@Configuration
@Import({LoggerConfig.class, OtherConfig.class})
public class AppConfig {

    @Resource
    private LoggerConfig loggerConfig;

    @Resource
    private Client client;

    @Bean
    public App app() {
        return new App(client, loggerConfig.loggersMap());
    }

}
