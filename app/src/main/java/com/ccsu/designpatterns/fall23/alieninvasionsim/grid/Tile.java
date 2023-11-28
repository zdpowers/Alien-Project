package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract data class which holds references to shared
 * data between different tile types in the simulation.
 *
 * @author Vincent Capra
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
        this.buffsDebuffs = new HashMap<>();
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
}
