package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

/**
 * Grid memento class
 * @author Zack Powers
 * @version 1.0
 * @since 2023-29-10
 */
public class GridMemento {
    private GridTile[][] gridState;

    /**
     * Constructor
     * @param gridState the state of the grid that is to be saved in the snapshot
     * @author Zack Powers
     * @since 2023-29-10
     */
    GridMemento(GridTile[][] gridState) {
        this.gridState = gridState;
    }

    /**
     * Method to get the grid state.
     *
     * @return returns the grid state as 2D array
     *
     * @author Zack Powers
     * @since 2023-29-10
     */
    GridTile[][] getGridState() {
        return gridState;
    }
}
