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
public abstract class Tile implements TileVisitable {
    private int mColumnPosition;
    private int mRowPosition;

    private LifeForm occupant;
    private boolean isOccupied = false;

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


    /**
     * Relocated the gridCell methods into this class. These are the methods that apply and remove the buffs or debuffs.
     *
     *
     *
     * @author Rocky Trinh
     * @version 1.0
     * @since 2023-05-12
     */

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


    @Override
    public abstract void accept(TileVisitor visitor);


    public int getmColumnPosition() {
        return mColumnPosition;
    }

    public int getmRowPosition() {
        return mRowPosition;
    }

    public void setmColumnPosition(int mColumnPosition) {
        this.mColumnPosition = mColumnPosition;
    }

    public void setmRowPosition(int mRowPosition) {
        this.mRowPosition = mRowPosition;
    }
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
