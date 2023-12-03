package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import java.util.Random;
import java.util.List;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;
import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;

public class HumanBaseReproductionStrat implements ReproduceStrategy{
    private LifeFormFactory lff = new LifeFormFactory();


    /**
     *
     * This is a concrete strategy method for the ReproduceStrategy interface. For humans,
     * 20% chance each year that the population will increase by 1 / tile. If population is
     * at the tile max (5) will randomly spawn a new human lifeform with population = 1 in an
     * unoccupied adjacent tile
     *
     * @return int signifying the updated population value of the tile.
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-11-28
     */
    public int reproduceStratMethod(int starting_population, LifeForm current_lifeform) {
        Random randomNum = new Random();
        List<TerrainTile> neighboring_tiles = current_lifeform.getNeighboringTerrain();

        // VC - if a neighboring tile is occupied, remove it
        for(TerrainTile tile : neighboring_tiles){
            //if (tile.tileIsOccupied()) neighboring_tiles.remove(tile);
            if (tile.getOccupant() != null) neighboring_tiles.remove(tile);
        }

        // VC - if the current tile population is over 5 and spawn a new person, spawn them
        //  into an adjacent tile.
        if(starting_population >= 5 && !(neighboring_tiles.isEmpty()) ){ // and there is an unoccupied neighboring terrain tile
            //&& randomNum.nextInt(5) == 3 &&
            //VC -
            int randomIndex = randomNum.nextInt(neighboring_tiles.size());
            TerrainTile temp_tile = neighboring_tiles.get(randomIndex);
            LifeForm temp_human = lff.makeLifeForm(Human.class.toString(), temp_tile);
            temp_tile.setOccupant(temp_human);
            Grid temp_grid = Grid.getInstance(10);
            temp_grid.addToGridLifeForms(temp_human); //mLifeForms.add(temp_tile.getOccupant());
        }


        // VC - if there are 2 humans and some RNG to increase the population
        if (randomNum.nextInt(5) == 3){
            return starting_population + 1;
        }
        else return starting_population;
    }
}
