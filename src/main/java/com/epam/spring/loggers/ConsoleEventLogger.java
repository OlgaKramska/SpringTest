package com.epam.spring.loggers;

import com.epam.spring.beans.Event;

/**
 * Created by Olga_Kramska on 7/27/2016.
 */
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }
}
