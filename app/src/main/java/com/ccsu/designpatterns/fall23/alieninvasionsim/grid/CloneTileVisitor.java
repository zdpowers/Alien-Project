package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;

import java.util.ArrayList;
import java.util.List;

public class CloneTileVisitor implements TileVisitor {

    private List<Tile> gridClone;

    public CloneTileVisitor() { gridClone = new ArrayList<>(); }

    /**
     * This method is used to visit and create a deep copy of a TerrainTile
     * @param tile the tile to be visited
     * @return returns a copy of the tile that is visited
     */
    @Override
    public void visitTerrainTile(TerrainTile tile) {
        if(tile.getOccupant() != null) { // VC - if true that tile is occuied
            TerrainTile tileCopy = new TerrainTile(tile.getTileCoordinates()[0], tile.getTileCoordinates()[1]);
            tileCopy.setOccupant(tile.getOccupant().clone(tileCopy));
            gridClone.add(tileCopy);
        } else {
            TerrainTile tileCopy = new TerrainTile(tile.getTileCoordinates()[0], tile.getTileCoordinates()[1]);
            gridClone.add(tileCopy);
        }
    }

    @Override
    public void visitResourceTile(ResourceTile tile) {
        ResourceTile tileCopy = new ResourceTile(tile.getTileCoordinates()[0], tile.getTileCoordinates()[1], tile.getResourceType());
        gridClone.add(tileCopy);
    }

    /**
     * Returns the cloned grid
     * @return List of the cloned tiles
     */
    public List<Tile> getGridClone() {
        return gridClone;
    }
}
