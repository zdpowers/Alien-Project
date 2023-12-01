package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

public interface ReproduceStrategy {
    int reproduceStratMethod(int starting_population, LifeForm current_lifeform);
}
