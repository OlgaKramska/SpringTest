package com.epam.spring;

import com.epam.spring.beans.Client;
import com.epam.spring.beans.Event;
import com.epam.spring.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Olga_Kramska on 7/27/2016.
 */
public class App {
    private static ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    private Client client;
    private EventLogger logger;

    public App(Client client, EventLogger logger) {
        this.client = client;
        this.logger = logger;
    }

    public static void main(String[] args) {


        App app = (App) context.getBean("app");

        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
    }

    public void logEvent(String msg) {
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        Event event = (Event) context.getBean("event");
        event.setMessage(message);
        logger.logEvent(event);
    }
}
