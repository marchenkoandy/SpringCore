package spring.core.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import spring.core.beans.Client;
import spring.core.beans.Event;

import java.util.Date;

public class OtherConfig {

    @Value("1")
    private String id;

    @Value("John Smith")
    private String fullName;

    @Bean
    public Client client() {
        return new Client(id, fullName);
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date());
    }
}