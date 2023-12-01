package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

public class OneUpReproductionStrat implements ReproduceStrategy{
    @Override
    public int reproduceStratMethod(int starting_population, LifeForm current_lifeform) {
        if (starting_population < 10 ){
            return starting_population + 1;
        }

        //VC - for this, we expand in ALL available directions at once
/*        if(starting_population >= 5 && ){ // and there is ANY unoccupied neighboring terrain tile
            return 100;
        }*/
        return starting_population;
    }
}
