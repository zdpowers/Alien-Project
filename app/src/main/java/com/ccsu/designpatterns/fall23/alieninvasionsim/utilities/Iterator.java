package com.ccsu.designpatterns.fall23.alieninvasionsim.utilities;

/**
 * Interface for iterating over collections of elements
 * @author Zack Powers
 * @version 1.0
 * @since 2023-30-11
 */
public interface Iterator<E> {
    /**
     * Returns True/false based on if there is another element in collection
     * @return true is there is another element in the collection, else false
     */
    boolean hasNext();

    /**
     * Returns the next element in the collection
     * @return the next object in the collection
     * @throws 'NoSuchElementException' if there is not another element
     */
    E next();
}
