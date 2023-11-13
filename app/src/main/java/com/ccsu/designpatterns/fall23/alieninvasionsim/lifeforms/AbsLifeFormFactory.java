package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

/**
 * An interface to define various factories to create different
 * types of lifeforms.
 * In the Sprint 1 Abstract Factory pattern this is
 * the Abstract Fact participant
 *
 * @author Vincent Capra
 * @version 1.0
 * @since 2023-10-29
 */
public interface AbsLifeFormFactory {

    /**
     * Create and return a LifeForm with the
     * supplied parameters and spawn tile.
     *
     * @param params    Parameters for the LifeForm as a String.
     * @param spawnTile Tile to place the LifeForm in.
     * @return lifeForm An instance of a LifeForm.
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-10-26
     */
    LifeForm makeLifeForm(String params, TerrainTile spawnTile);
}
