package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

/**
 * A class to define the behavior of Saiyan Alien
 * lifeforms within the simulation
 *
 * @author Vincent Capra, Joseph Lumpkin, Zack Powers
 */
public class Saiyan extends LifeForm {

    Saiyan(TerrainTile spawn_tile) {
        super(spawn_tile);
    }

    /**
     * Prototype constructor. This constructor is used when using the clone method to create a copy of a LifeForm object
     * @param source the source object which is to be cloned. The new object is initialized with the parameters of the source object.
     * @author Zack Powers
     * @since 2023-26-10
     */
    Saiyan(Saiyan source) {
        super(source);
    }

    @Override
    protected void gather() {

    }

    @Override
    protected void reproduce() {

    }

    @Override
    protected void move() {

    }

    @Override
    protected void attack(Grid grid) {

    }

    @Override
    protected void defend(int damage) {

    }

    @Override
    public LifeForm clone() {
        return new Saiyan(this);
    }
}
