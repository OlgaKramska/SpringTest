package com.epam.spring;

import com.epam.spring.aspects.StatisticAspect;
import com.epam.spring.beans.Client;
import com.epam.spring.beans.Event;
import com.epam.spring.loggers.ConsoleEventLogger;
import com.epam.spring.loggers.EventLogger;
import com.epam.spring.type.EventType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Map;

/**
 * Created by Olga_Kramska on 7/27/2016.
 */
public class App {
    private Client client;
    private Map<EventType, EventLogger> loggerMap;
    private EventLogger defaultLogger;

    public App(Client client, Map<EventType, EventLogger> loggerMap, EventLogger defaultLogger) {
        this.client = client;
        this.loggerMap = loggerMap;
        this.defaultLogger = defaultLogger;
    }

    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("spring.xml");

        App app = context.getBean("app", App.class);

        Event event1 = context.getBean("event", Event.class);
        event1.setMessage("Some event for user 1");
        Event event2 = context.getBean("event", Event.class);
        event2.setMessage("Some event for user 2");
        Event event3 = context.getBean("event", Event.class);
        event3.setMessage("Some event for user 3");
        Event event4 = context.getBean("event", Event.class);
        event3.setMessage("Some event for user 4");

        app.logEvent(event1, "error");
        app.logEvent(event2, "info");
        app.logEvent(event3, "info");
        app.logEvent(event4, "info");


    }

    public void logEvent(Event event, String loggerType) {
        String message = event.getMessage().replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMessage(message);
        EventLogger logger;
        if (EventType.ERROR.toString().equalsIgnoreCase(loggerType)){
            logger = loggerMap.get(EventType.ERROR);
        } else if (EventType.INFO.toString().equalsIgnoreCase(loggerType)){
            logger = loggerMap.get(EventType.INFO);
        } else {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
