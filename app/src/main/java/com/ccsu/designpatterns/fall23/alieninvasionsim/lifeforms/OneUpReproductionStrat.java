package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

import java.util.List;

public class OneUpReproductionStrat implements ReproduceStrategy{
    private LifeFormFactory lff = new LifeFormFactory();
    /**
     *
     * This is a concrete strategy method for the ReproduceStrategy interface. For humans next to a
     * one-up mushroom, each year the population will increase by 1 / tile. If population is
     * at the 1-up tile max (10) will spawn a new human lifeform with population = 1 in EVERY
     * unoccupied adjacent tile.
     *
     * @return int signifying the updated population value of the tile.
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-11-28
     */

    @Override
    public int reproduceStratMethod(int starting_population, LifeForm current_lifeform) {
        List<TerrainTile> neighboring_tiles = current_lifeform.getNeighboringTerrain();

        if (starting_population < 10 ){
            System.out.println("Human at " + current_lifeform.getTileOfResidence() +
                    "is reproducing with 1-up");
            return starting_population + 1;
        }

        if(starting_population >= 10 && !(neighboring_tiles.isEmpty()) ){ // and there is an unoccupied neighboring terrain tile
            for( TerrainTile temp_tile : neighboring_tiles){
                LifeForm temp_human = lff.makeLifeForm(Human.class.toString(), temp_tile);
                temp_tile.setOccupant(temp_human);
                Grid temp_grid = Grid.getInstance(10);
                temp_grid.addToGridLifeForms(temp_human); //mLifeForms.add(temp_tile.getOccupant());
            }
        }
        return starting_population;
    }
}
