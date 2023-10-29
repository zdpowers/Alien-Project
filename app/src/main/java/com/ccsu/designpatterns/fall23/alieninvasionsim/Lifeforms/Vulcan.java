package com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.TerrainTile;

/**
 * A class to define the behavior of Vulcan Alien
 * lifeforms within the simulation
 *
 * @author Vincent Capra
 */
public class Vulcan extends LifeForm {

    Vulcan(TerrainTile spawn_tile) {
        super(spawn_tile);
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
}
