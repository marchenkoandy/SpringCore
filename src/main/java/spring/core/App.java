package spring.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.beans.Client;
import spring.core.beans.Event;
import spring.core.enums.EventType;
import spring.core.loggers.EventLogger;

import java.util.List;
import java.util.Map;
import java.util.Observable;

public class App extends Observable {

    @Autowired
    private Client client;

    private Map<EventType, EventLogger> eventLoggers;

    @Autowired
    private static ConfigurableApplicationContext ctx;


    public void logEvent(String msg, EventType eventType) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = ctx.getBean(Event.class);
        event.setMsg(message);
        for (Map.Entry<EventType, EventLogger> eventLogger : eventLoggers.entrySet()) {
//            eventLogger.logEvent(event);
        }
    }

    public App(Client client, Map<EventType, EventLogger> eventLoggers) {
        this.client = client;
        this.eventLoggers = eventLoggers;
    }


    public static void main(String[] args) {
        System.out.println("TEST");

//        ctx = new ClassPathXmlApplicationContext("Schema.xml");
//        App app = ctx.getBean(App.class);
//
//        for(int i=0; i<9;i++) {
//            app.logEvent("Some event for user 1",EventType.INFO);
//        }
//        ctx.close();
    }
}
