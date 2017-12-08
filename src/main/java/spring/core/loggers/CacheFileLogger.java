package spring.core.loggers;

import spring.core.beans.Event;

import java.util.List;

public class CacheFileLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size()==cacheSize){

        }
        super.logEvent(event);
    }
}
