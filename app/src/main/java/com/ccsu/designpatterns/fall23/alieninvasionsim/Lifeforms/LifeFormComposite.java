package com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.TerrainTile;

import java.util.ArrayList;

public class LifeFormComposite extends LifeForm{
    protected ArrayList<LifeForm> tribeMembers = new ArrayList();
    private String tribeDescription;
    LifeFormComposite(TerrainTile spawn_tile) {
        super(spawn_tile);
        // VC - Setting the tribe description to be based on its tile
        tribeDescription = "Tribe at Tile - " + spawn_tile.toString();
    }

    @Override
    protected void gather() {}

    @Override
    protected void reproduce() {}

    @Override
    protected void move() {}

    @Override
    protected void attack(Grid grid) {}

    @Override
    protected void defend(int damage) {}
}
