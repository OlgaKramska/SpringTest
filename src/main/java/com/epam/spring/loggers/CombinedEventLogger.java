package com.epam.spring.loggers;

import com.epam.spring.beans.Event;

import java.util.List;

/**
 * Created by Olga_Kramska on 8/23/2016.
 */
public class CombinedEventLogger implements EventLogger{
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for (EventLogger logger:loggers) {
            logger.logEvent(event);
        }
    }
}
