package com.epam.spring;

import com.epam.spring.beans.Client;
import com.epam.spring.beans.Event;
import com.epam.spring.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Olga_Kramska on 7/27/2016.
 */
public class App {
    private Client client;
    private EventLogger logger;

    public App(Client client, EventLogger logger) {
        this.client = client;
        this.logger = logger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) context.getBean("app");

        Event event1 = (Event) context.getBean("event");
        event1.setMessage("Some event for user 1");
        Event event2 = (Event) context.getBean("event");
        event2.setMessage("Some event for user 2");

        app.logEvent(event1);
        app.logEvent(event2);
        context.close();
    }

    public void logEvent(Event event) {
        String message = event.getMessage().replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMessage(message);
        logger.logEvent(event);
    }
}
