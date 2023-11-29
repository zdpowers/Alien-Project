package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

/**
 * The TileVisitor interface declares a set of visiting methods that correspond to type of tile
 * @author Zack Powers
 * @version 1.0
 * @since 2023-29-11
 */
public interface TileVisitor {
    /**
     * Method to visit Terrain Tiles
     * @param tile the tile to be visited
     * @author Zack Powers
     * @since 2023-29-11
     */
    TerrainTile visitTerrainTile(TerrainTile tile);

    /**
     * Method to visit Resource Tiles
     * @param tile the tile to be visited
     * @author Zack Powers
     * @since 2023-29-11
     */
    ResourceTile visitResourceTile(ResourceTile tile);
}
