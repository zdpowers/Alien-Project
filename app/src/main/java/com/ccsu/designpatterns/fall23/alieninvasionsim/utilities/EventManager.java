package com.ccsu.designpatterns.fall23.alieninvasionsim.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class acts special helper object devoted maintaining subscription lists of listeners for the Observer pattern.
 * The Publisher object holds onto a reference of this object and calls its methods to add/remove/notify subscribers.
 * @author Zack Powers
 * @version 1.0
 * @since 2023-11-12
 */
public class EventManager {

    /**
     * Hashmap containing an ArrayList of EventListeners as a value associated with an eventType as a key
     */
    private HashMap<String, ArrayList<EventListener>> listeners = new HashMap<>();

    /**
     * Constructor.
     */
    public EventManager(){}

    /**
     * This method subscribes the EventListener object to eventType that is passed to this method.
     * @param eventType a String identifier of what type of event is occurring, used to obtain list of objects listening for the event.
     * @param listener an object that implements the EventListener interface, it is registered as a listener of the EventType passed to this method.
     * @author Zack Powers
     * @since 2023-11-26
     */
    public void subscribe(String eventType, EventListener listener) {
        Objects.requireNonNull(listeners.get(eventType)).add(listener);
    }

    /**
     * This method unsubscribes the EventListener object to eventType that is passed to this method.
     * @param eventType a String identifier of what type of event is occurring, used to obtain list of objects listening for the event.
     * @param listener an object that implements the EventListener interface, it is registered as a listener of the EventType passed to this method.
     * @author Zack Powers
     * @since 2023-11-26
     */
    public void unsubscribe(String eventType, EventListener listener) {
        Objects.requireNonNull(listeners.get(eventType)).remove(listener);
    }

    /**
     * This method notifies and passed data to all listeners of a a specific event.
     * @param eventType a String identifier of what type of event is occurring, used to obtain list of objects listening for the event.
     * @param data a String representation of data/context associated with the event that is passed to the listeners.
     * @author Zack Powers
     * @since 2023-11-26
     */
    public void notify(String eventType, String data) {
        for(EventListener listener : Objects.requireNonNull(listeners.get(eventType))) {
            listener.update(data);
        }
    }
}
