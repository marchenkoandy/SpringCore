package spring.core;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.beans.Client;
import spring.core.beans.Event;
import spring.core.loggers.CacheFileLogger;
import spring.core.loggers.EventLogger;
import spring.core.loggers.FileEventLogger;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class App extends Observable {

    private Client client;
    private EventLogger eventLogger;
    private EventLogger fileEventLogger;
    private static ApplicationContext ctx;

    public void setEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = ctx.getBean(Event.class);
        event.setMsg(message);
        eventLogger.logEvent(event);
        fileEventLogger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger, EventLogger fileEventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.fileEventLogger = fileEventLogger;
    }


    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("Schema.xml");
        App app = ctx.getBean(App.class);

        for(int i=0; i<10;i++) {
            app.setEvent("Some event for user 1");
        }
    }
}
