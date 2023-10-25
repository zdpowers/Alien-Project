package com.ccsu.designpatterns.fall23.alieninvasionsim.Gridfeatures;

import android.content.Context;
/**
 * A class to build the simulation grid
 *
 * @author Vincent Capra
 */
public class GridBuilder {
    private int numberOfTilesInGrid = 0;
    private GridTile[][] gridLayout;

    /**
     * This method initializes the grid layout based on a given
     * dimension. Then Calls subsequent methods to place water
     * and other resources on the map
     *
     * @author Vincent Capra
     * @param gridSize the number of tiles to make a square
     *                 where length of side = gridSize
     */
    public GridBuilder(int gridSize) {
        gridLayout = new GridTile[gridSize][gridSize];

        // VPC - loop through the rows
        for (int i = 1; i <= gridSize; i++) {

            //loop through the columns adding tiles and setting neighbors
            for (int j = 1; j <= gridSize; j++) {
                //VPC - create the grid tile for current location
                gridLayout[i][j] = new GridTile(j, i);
                numberOfTilesInGrid++; //this might not be necessary...?
            }
            //GridTile imageViewX = new GridTile();
        }
        placeWaterTiles();
        placeResourceTiles();
    }

    /**
     * This method places connected water tiles in the map based on seed values
     * until ~30% of the map is water
     *
     * @author Vincent Capra
     */
    private void placeWaterTiles(){
        int maxNumOfWaterTiles = numberOfTilesInGrid/3; //place water tiles
        int currentNumOfWaterTiles = 0;

        while (currentNumOfWaterTiles <= maxNumOfWaterTiles) {

            // VPC - need a collection here to easily figure out if a
            // tile is a already a water tile or not...
            // maybe needs to be private class var so we can check when placing
            // resource tiles in placeResourceTiles method too.

            // need to randomize seeds 1, 2, 3 and then start replacing water tiles
        }
    }

    private void placeResourceTiles(){

    }
}