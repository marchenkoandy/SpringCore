package spring.core.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import spring.core.App;
import spring.core.beans.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName){
        this.fileName = fileName;
        this.file = new File(this.fileName);
    }

    private void writeEventToFile(Event event){
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(event);
        writeEventsToFile(eventList);
    }

    protected void writeEventsToFile(List<Event> eventList) {
        StringBuilder eventsToString = new StringBuilder();
        for (Event event: eventList){
            eventsToString.append(event.toString());
            eventsToString.append("\n");
        }
        try {
            FileUtils.writeStringToFile(file, eventsToString.toString(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logEvent(Event event) {
       writeEventToFile(event);
    }
}
