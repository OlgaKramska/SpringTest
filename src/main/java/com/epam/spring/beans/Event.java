package com.epam.spring.beans;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Olga_Kramska on 7/27/2016.
 */
public class Event {
    private static int counter;
    private int id;
    private String message;
    private Date date;
    private DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        counter++;
        id = counter;
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public static boolean isDay() {
        if (new Date().getHours() > 8 && new Date().getHours() < 17) {
            return true;
        } else {
            return false;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + dateFormat.format(date) +
                '}' + '\n';
    }
}
