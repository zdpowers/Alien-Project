package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;

public class CloneTileVisitor implements TileVisitor {

    public CloneTileVisitor() {}

    /**
     * This method is used to visit and create a deep copy of a TerrainTile
     * @param tile the tile to be visited
     * @return returns a copy of the tile that is visited
     */
    @Override
    public TerrainTile visitTerrainTile(TerrainTile tile) {
        if(tile.tileIsOccupied()) {
            TerrainTile tileCopy = new TerrainTile(tile.getTileCoordinates()[0], tile.getTileCoordinates()[1]);
            tileCopy.setOccupant(tile.getOccupant().clone(tileCopy));
            return tileCopy;
        } else {
            return new TerrainTile(tile.getTileCoordinates()[0], tile.getTileCoordinates()[1]);
        }
    }

    @Override
    public ResourceTile visitResourceTile(ResourceTile tile) {
        return new ResourceTile(tile.getTileCoordinates()[0], tile.getTileCoordinates()[1], tile.getResourceType());
    }
}
