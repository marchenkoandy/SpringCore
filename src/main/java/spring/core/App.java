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
//    private EventLogger eventLogger;
//    private EventLogger fileEventLogger;
    private Event event;
    private ApplicationContext ctx;

    public void setEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event = ctx.getBean(Event.class);
        event.setMsg(message);
        setChanged();
        notifyObservers();
//        eventLogger.logEvent(event);
//        fileEventLogger.logEvent(event);
    }

    public Event getEvent() {
        return event;
    }

    public App(){}

    public App(Client client) {
        this.client = client;
//        this.eventLogger = eventLogger;
//        this.fileEventLogger = fileEventLogger;
    }

    public void main(){
        ctx = new ClassPathXmlApplicationContext("Schema.xml");
        App app = ctx.getBean(App.class);
        EventLogger eventLogger = ctx.getBean(FileEventLogger.class);
//        EventLogger fileEventLogger = ctx.getBean(CacheFileLogger.class);
        app.addObserver((Observer) eventLogger);
//        app.addObserver((Observer) fileEventLogger);

        for(int i=0; i<10;i++) {
            app.setEvent("Some event for user 1");
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.main();
    }
}
