package com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.TerrainTile;

/**
 * A class to define the behavior of Xenomorph Alien
 * lifeforms within the simulation
 *
 * @author Vincent Capra
 */
public class Xenomorph extends LifeForm {

    public Xenomorph(TerrainTile spawn_Tile) {
        super(spawn_Tile);
    }
    private TerrainTile tileOfResidence;

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
}


