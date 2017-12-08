package spring.core.loggers;

import spring.core.beans.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CacheFileLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache = new ArrayList<Event>();

    public CacheFileLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if(cache.size()==cacheSize){
            writeEventsToFile(cache);
            cache.clear();
        }
    }
}
