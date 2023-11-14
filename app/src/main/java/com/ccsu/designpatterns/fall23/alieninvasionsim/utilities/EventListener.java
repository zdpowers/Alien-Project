package com.ccsu.designpatterns.fall23.alieninvasionsim.utilities;

/**
 * This is an interface that is to be implemented by any object that needs to be subscribed to the EventManager as a listener.
 * @author Zack Powers
 * @version 1.0
 * @since 2023-11-12
 */
public interface EventListener {
    /**
     * This method is called by the EventManager to notify subscribers/listeners when a specific change/event occurs with the publisher.
     * @param data a String representation of data/context associated with the event that is passed to the listener.
     * @author Zack Powers
     * @since 2023-11-12
     */
    void update(String data);
}
