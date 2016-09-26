package com.epam.spring;

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
//        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
//        context.load("classpath:src/main/resources/spring.xml");
//        context.refresh();

        App app = (App) context.getBean("app");

        Event event1 = (Event) context.getBean("event");
        event1.setMessage("Some event for user 1");
        Event event2 = (Event) context.getBean("event");
        event2.setMessage("Some event for user 2");

        app.logEvent2(event1, "error");
        app.logEvent2(event2, "info");
        app.logEvent2(event2, "bla-bla");

//        context.close();
    }

    public void logEvent2(Event event, String loggerType) {
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
