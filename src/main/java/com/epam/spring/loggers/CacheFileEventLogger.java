package com.epam.spring.loggers;

import com.epam.spring.beans.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga_Kramska on 7/29/2016.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<Event>(cacheSize);
    }

    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventFromCache();
            cache.clear();
        }
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventFromCache();
        }
    }

    private void writeEventFromCache() {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }
}
