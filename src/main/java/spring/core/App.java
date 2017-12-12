package spring.core;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.beans.Client;
import spring.core.beans.Event;
import spring.core.configurations.AppConfig;
import spring.core.enums.EventType;
import spring.core.loggers.EventLogger;
import java.util.Map;
import java.util.Observable;

public class App extends Observable {

    private Client client;

    @Autowired
    private ObjectFactory<Event> eventsFactory;

    private Map<EventType, EventLogger> eventLoggers;

    public void logEvent(String msg, EventType eventType) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = eventsFactory.getObject();
        event.setMsg(message);
        eventLoggers.get(eventType).logEvent(event);
    }

    public App(Client client, Map<EventType, EventLogger> eventLoggers) {
        this.client = client;
        this.eventLoggers = eventLoggers;
    }

    public void main(){
        for(int i=0; i<10;i++) {
            this.logEvent("Some event for user 1",EventType.ALL);
        }
    }


    public static void main(String[] args) {
        System.out.println("TEST");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = context.getBean(App.class);
        app.main();
        System.out.println("THE END");
    }
}
