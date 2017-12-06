package spring.core;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.beans.Client;
import spring.core.beans.Event;
import spring.core.loggers.EventLogger;

public class App {


    private Event event;
    private Client client;
    private EventLogger eventLogger;

    public void logEvent(String msg) {
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
//        app.client = new Client("1","John Smith");
//        app.eventLogger = new ConsoleEventLogger();
////
        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
    }

}
