package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

/**
 * A class to define the behavior of Human lifeforms within the simulation
 *
 * @author Vincent Capra
 */
public class Human extends LifeForm {

    Human(TerrainTile spawn_tile) {
        super(spawn_tile);
    }

    @Override
    protected void gather() {

    }

    /**
     * method to possibly add another human to the tile that this
     * human currently inhabits
     *
     * @author Vincent Capra
     * @return boolean true if occupied
     */
    @Override
    protected void reproduce() {
        //VC - Need to run some chance that this human reproduces,
        // then if true, call the TerrainTile that this human is in
        // for method

        tileOfResidence.generateLifeform(); //VC - may need to pass in the type?
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
}
