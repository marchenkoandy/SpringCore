package spring.core;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.beans.Client;
import spring.core.loggers.EventLogger;

public class App {


    private Client client;
    private EventLogger eventLogger;

    public void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Schema.xml");
        App app = (App) ctx.getBean(App.class);
//        app.client = new Client("1","John Smith");
//        app.eventLogger = new ConsoleEventLogger();
////
        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
    }

}
