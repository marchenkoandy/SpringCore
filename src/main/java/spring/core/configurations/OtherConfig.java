package spring.core.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.beans.Client;
import spring.core.beans.Event;

@Configuration
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
    public Event event() {
        return new Event();
    }
}