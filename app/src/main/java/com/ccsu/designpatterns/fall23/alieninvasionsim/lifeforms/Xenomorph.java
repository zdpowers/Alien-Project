package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

/**
 * A class to define the behavior of Xenomorph Alien
 * lifeforms within the simulation
 *
 * @author Vincent Capra, Joseph Lumpkin, Zack Powers
 */
public class Xenomorph extends LifeForm {

    public Xenomorph(TerrainTile spawn_Tile) {
        super(spawn_Tile);
    }

    /**
     * Prototype constructor. This constructor is used when using the clone method to create a copy of a LifeForm object
     * @param source the source object which is to be cloned. The new object is initialized with the parameters of the source object.
     * @param residence the new TerrainTile that the LifeForm will be cloned to
     * @author Zack Powers
     * @since 2023-12-11
     */
    public Xenomorph(Xenomorph source, TerrainTile residence) {
        super(source, residence);
    }
    private TerrainTile tileOfResidence;

    @Override
    protected void reproduce() {

    }

    @Override
    protected void checkForApplicableResources() {

    }

    @Override
    protected void move() {

    }

    @Override
    protected void mine(int[] current_coordinates) {

    }

    @Override
    protected void attack(Grid grid) {

    }

    @Override
    protected void defend(int damage) {

    }

    @Override
    public LifeForm clone(TerrainTile residence) {
        return new Xenomorph(this, residence);
    }
}


