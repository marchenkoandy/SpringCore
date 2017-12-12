package spring.core.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import spring.core.App;
import spring.core.beans.Client;

import javax.annotation.Resource;

@Import({LoggerConfig.class, OtherConfig.class})
@PropertySource({"classpath:/loggers.properties", "classpath:/client.properties"})
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
