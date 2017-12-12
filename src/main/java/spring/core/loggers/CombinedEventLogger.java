package spring.core.loggers;

import spring.core.beans.Event;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger{
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> eventLoggers){
        this.loggers = eventLoggers;
    }

    public void logEvent(Event event) {
        for (EventLogger logger: loggers) {
            logger.logEvent(event);
        }
    }
}
