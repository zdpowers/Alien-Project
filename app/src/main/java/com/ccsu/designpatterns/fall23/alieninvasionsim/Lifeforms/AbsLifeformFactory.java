package com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.TerrainTile;

/**
 * An interface to define various factories to create different
 * types of lifeforms.
 * In the Sprint 1 Abstract Factory pattern this is
 * the Abstract Fact participant
 *
 * @author Vincent Capra
 */
public interface AbsLifeformFactory {

    public LifeForm makeLifeform(String params, TerrainTile spawn_tile);
}
