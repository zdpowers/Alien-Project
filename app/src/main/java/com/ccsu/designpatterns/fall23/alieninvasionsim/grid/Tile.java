package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;

/**
 * Abstract data class which holds references to shared
 * data between different tile types in the simulation.
 *
 * @author Vincent Capra, Zack Powers
 * @version 1.0
 * @since 2023-10-26
 */
public abstract class Tile {
    int mColumnPosition;
    int mRowPosition;

    LifeForm occupant;

    public Tile(int column, int row) {
        // Need to place the tile in the layout visually
        mColumnPosition = column;
        mRowPosition = row;
    }

    public int[] getTileCoordinates(){
        return new int[]{mColumnPosition, mRowPosition};
    }

    public LifeForm getOccupant() {
        return occupant;
    }

    public void setOccupant(LifeForm occupant) {
        this.occupant = occupant;
    }

    /**
     * Method to accept visitor objects.
     * @param visitor the Concrete implementation of TileVisitor that is visiting the object.
     * @return Tile because the visitor is making a clone of the tile.
     * @author Zack Powers
     * @version 1.o
     */
    public abstract void accept(TileVisitor visitor);

    /**
     * Construct a proper toString method for the class
     *
     * @author Vincent Capra
     * @return String indicating the tile description
     * @version 1.0
     * @since 2023-11-6
     */
    @Override
    public String toString() {
        return "Column: " + this.mColumnPosition +  ", Row: " + this.mRowPosition;
    }
}
