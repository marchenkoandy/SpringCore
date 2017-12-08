package spring.core;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.beans.Client;
import spring.core.beans.Event;
import spring.core.loggers.EventLogger;

import java.util.Observable;

public class App extends Observable {

    private Client client;
    private EventLogger eventLogger;
    private EventLogger fileEventLogger;
    private EventLogger cacheFileEventLogger;
    private static ApplicationContext ctx;

    public void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = ctx.getBean(Event.class);
        event.setMsg(message);
        eventLogger.logEvent(event);
        fileEventLogger.logEvent(event);
        cacheFileEventLogger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger, EventLogger fileEventLogger, EventLogger cacheFileEventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.fileEventLogger = fileEventLogger;
        this.cacheFileEventLogger= cacheFileEventLogger;
    }


    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("Schema.xml");
        App app = ctx.getBean(App.class);

        for(int i=0; i<10;i++) {
            app.logEvent("Some event for user 1");
        }
    }
}
