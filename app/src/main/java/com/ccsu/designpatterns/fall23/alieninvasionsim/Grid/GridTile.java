package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

/**
 * Abstract data class which holds references to shared
 * data between different tile types in the simulation.
 *
 * @author Vincent Capra
 * @version 1.0
 * @since 2023-10-26
 */
public abstract class GridTile {
    int mColumnPosition;
    int mRowPosition;

    public GridTile(int column, int row) {
        // Need to place the tile in the layout visually
        mColumnPosition = column;
        mRowPosition = row;
    }
}
