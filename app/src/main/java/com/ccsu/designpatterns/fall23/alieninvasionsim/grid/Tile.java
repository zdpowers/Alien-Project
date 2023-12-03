package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;

import java.util.HashMap;
import java.util.Map;

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

//    /** Flag to tell if this tile is susceptible to current weather events */
//    private boolean mWeatherFlag;

    LifeForm occupant;

    public Tile(int column, int row) {
        // Need to place the tile in the layout visually
        mColumnPosition = column;
        mRowPosition = row;
        this.buffsDebuffs = new HashMap<>();
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


    //These are the buff and debuff methods

    private Map<BuffDebuffTypes, Integer> buffsDebuffs;

    public void applyBuffDebuff(BuffDebuffTypes type, int value) {
        buffsDebuffs.put(type, value);
    }

    public void removeBuffDebuff(BuffDebuffTypes type) {
        buffsDebuffs.remove(type);
    }

    public int getBuffDebuffValue(BuffDebuffTypes type) {
        return buffsDebuffs.getOrDefault(type, 0);
    }

//    public void setWeatherFlag(boolean weatherFlag) {
//        mWeatherFlag = weatherFlag;
//    }
//
//    public boolean getWeatherFlag() {
//        return mWeatherFlag;
//    }

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
