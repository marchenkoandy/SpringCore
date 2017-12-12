package spring.core.loggers;


import org.springframework.stereotype.Component;
import spring.core.beans.Event;

public class ConsoleEventLogger implements EventLogger{
    public void logEvent(Event event){
        System.out.println(event.toString());
    }
}
