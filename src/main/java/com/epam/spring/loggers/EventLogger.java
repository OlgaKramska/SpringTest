package com.epam.spring.loggers;

import com.epam.spring.beans.Event;

/**
 * Created by Olga_Kramska on 7/27/2016.
 */
public interface EventLogger {
    void logEvent(Event event);
}
