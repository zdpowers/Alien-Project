package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

/**
 * This class is a representation of the grid of life-form and terrain tiles that is
 * displayed in the application
 * @author Joseph Lumpkin, Zack Powers
 * @version 1.0
 * @since 2023-26-10
 */
public class Grid {
    private Object[][] grid;

    /**
     * Constructor
     */
    public Grid() {}

    /**
     * NOTE: NEED TO EDIT THIS TO MAKE DEEP COPY OF THE GRID
     * Method to create memento object to save the grid's state
     * @return returns a memento object representing the grid's state
     * @author Zack Powers
     * @since 2023-29-10
     */
    public Memento save() {
        return new Memento(grid);
    }

    /**
     * Method to restore the grid to a previous state from a memento object
     * @param state a memento object containing a grid state
     * @author Zack Powers
     * @since 2023-29-10
     */
    public void restore(Memento state) {
        grid = state.getGridState();
    }

    /**
     * Grid memento class
     * @author Zack Powers
     * @version 1.0
     * @since 2023-29-10
     */
    public static class Memento {
        private Object[][] gridState;

        /**
         * Constructor
         * @param gridState the state of the grid that is to be saved in the snapshot
         * @author Zack Powers
         * @since 2023-29-10
         */
        private Memento(Object[][] gridState) {
            this.gridState = gridState;
        }

        /**
         * Method to get the grid state
         * @return returns the grid state as 2D array
         * @author Zack Powers
         * @since 2023-29-10
         */
        private Object[][] getGridState() {
            return gridState;
        }
    }


}
