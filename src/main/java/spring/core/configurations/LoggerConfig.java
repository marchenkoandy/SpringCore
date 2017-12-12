package spring.core.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.enums.EventType;
import spring.core.loggers.*;

import java.util.*;

public class LoggerConfig {

    public Map<EventType, EventLogger> loggersMap() {
        final Map<EventType, EventLogger> loggersMap = new HashMap<EventType, EventLogger>() {{
            put(EventType.INFO, consoleEventLogger());
            put(EventType.ERROR, fileEventLogger());
            put(EventType.DEFAULT, cacheFileLogger());
            put(EventType.ALL, combinedEventLogger());
        }};
        return loggersMap;
    }
    private Set loggersSet() {
        Set loggersSet = new HashSet() {{
            add(consoleEventLogger());
            add(fileEventLogger());
            add(cacheFileLogger());
        }};
        return loggersSet;
    }

    @Value("target/springtest/log.txt")
    private String logFile;

    @Value("target/springtest/cache_log.txt")
    private String cacheLogFile;

    @Value("5")
    private int cacheSize;

    @Bean
    public EventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    public EventLogger fileEventLogger() {
        return new FileEventLogger(logFile);
    }

    @Bean
    public EventLogger cacheFileLogger() {
        return new CacheFileLogger(cacheLogFile, cacheSize);
    }

    @Bean
    public EventLogger combinedEventLogger() {
        return new CombinedEventLogger(loggersSet());
    }
}
