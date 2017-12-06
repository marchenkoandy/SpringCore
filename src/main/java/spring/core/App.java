package spring.core;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.beans.Client;
import spring.core.beans.Event;
import spring.core.loggers.EventLogger;

public class App {

    private Client client;
    private EventLogger eventLogger;

    public void logEvent(Event event,String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Schema.xml");
        App app = ctx.getBean(App.class);
        for(int i=0; i<10;i++) {
            Event event = ctx.getBean(Event.class);
            app.logEvent(event, "Some event for user 1");
        }
    }
}
