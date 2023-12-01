package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import java.util.Stack;

/**
 * The object that keeps track of multiple memento. Like maintaining save-points.
 * @author Zack Powers
 * @version 1.0
 * @since 2023-29-10
 */
public class GridCaretaker {

    private Stack<Grid.GridMemento> mementoList = new Stack<>();

    /**
     * Constructor
     */
    public GridCaretaker() {}

    /**
     * Method used to add new memento object to the stack
     * @param state memento object to be added to the stack
     * @author Zack Powers
     * @since 2023-29-10
     */
    public void add(Grid.GridMemento state) {
        mementoList.push(state);
    }

    /**
     * Method used to pop the latest memento object from the stack
     * @return returns a memento object containing the grids state
     * @author Zack Powers
     * @since 2023-29-10
     */
    public Grid.GridMemento get() {
        return mementoList.pop();
    }

    /**
     * Get the size of the memento list.
     *
     * @return length   - The length of the memento list.
     *
     * @author Joseph Lumpkin
     * @version 1.0
     * @since 2023-11-28
     */
    public int getLength() {
        return mementoList.capacity();
    }
}
