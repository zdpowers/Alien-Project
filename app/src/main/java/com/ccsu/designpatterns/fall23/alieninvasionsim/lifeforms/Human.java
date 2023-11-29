package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;
import java.util.Random;

/**
 * A class to define the behavior of Human lifeforms within the simulation
 *
 * @author Vincent Capra, Joseph Lumpkin, Zack Powers
 */
public class Human extends LifeForm {

    Human(TerrainTile spawn_tile) {
        super(spawn_tile);
    }

    /**
     * Prototype constructor. This constructor is used when using the clone method to create a copy of a LifeForm object
     * @param source the source object which is to be cloned. The new object is initialized with the parameters of the source object.
     * @author Zack Powers
     * @since 2023-12-11
     */
    Human(Human source) {
        super(source);
    }

/*
    @Override
    protected void gather() {
        int[] currentCoordinates = super.getTileOfResidence().getTileCoordinates();

    }
*/

    /**
     * method to possibly add another human to the tile that this
     * human currently inhabits
     *
     * @author Vincent Capra
     * @return boolean true if occupied
     */
    @Override
    protected void reproduce() {
        Random randomNum = new Random();

        //VC - if there are 2 humans and some RNG to increase the population
        if (super.getPopulationCount() > 2
                && randomNum.nextInt(5) == 3){
            super.setPopulationCount(1);
        }

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
        return new Human(this);
    }
}
