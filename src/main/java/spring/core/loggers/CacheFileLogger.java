package spring.core.loggers;

import org.springframework.stereotype.Component;
import spring.core.beans.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("cacheFileLogger")
public class CacheFileLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache = new ArrayList<Event>();

    public CacheFileLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    private void destroy(){
        if (!cache.isEmpty()){
            writeEventsToFile(cache);
        }
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
