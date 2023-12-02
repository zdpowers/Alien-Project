package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

/**
 * An interface to detail all reproduce() strategies
 * that a Lifeform unit might need to handle.
 *
 * @author Vincent Capra
 * @version 1.0
 * @since 2023-1-12
 */
public interface ReproduceStrategy {
    int reproduceStratMethod(int starting_population, LifeForm current_lifeform);
}
