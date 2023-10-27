package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Arrays.*;

/**
 * A class to build the simulation grid
 *
 * @author Vincent Capra
 */
public class GridBuilder {
    private int numberOfTilesInGrid = 0, gridSize;
    //private int randomColumn, randomRow;
    private GridTile[][] gridLayout; //VC - is the format [row][column]

    //VC - during the build process we can use this to track what squares
    // already are resource tiles so we don't over-write
    HashSet<ResourceTile> resourceTileHashSet = new HashSet<ResourceTile>();

    /**
     * This method initializes the grid layout based on a given
     * dimension. Then Calls subsequent methods to place water
     * and other resources on the map
     *
     * @author Vincent Capra
     * @param grid_Size the number of tiles to make a square
     *                 where length of side = gridSize
     */
    public GridBuilder(int grid_Size) {
        gridLayout = new GridTile[grid_Size][grid_Size];
        gridSize = grid_Size;
        // VC - loop through the rows
        for (int row = 0; row < grid_Size; row++) {

            //loop through the columns adding tiles and setting neighbors
            for (int column = 0; column < grid_Size; column++) {
                //VC - create the grid tile for current location
                gridLayout[row][column] = new TerrainTile(column, row);
                numberOfTilesInGrid++; //this might not be necessary...?
            }
            //GridTile imageViewX = new GridTile();
        }
        placeWaterTiles();
        placeResourceTiles();
    }
    public int getGridSize(){
        return gridSize;
    }

    /**
     * This method places connected water tiles in the map based on seed values
     * until ~30% of the map is water
     *
     * @author Vincent Capra
     * @exception NoAvailableTilesException
     */
    private void placeWaterTiles(){
        int maxNumOfWaterTiles = numberOfTilesInGrid/3; //place water tiles
        int currentNumOfWaterTiles = 0;


        //VC - creating 3 unique pointers [row_pos, column_pos]
        int[] pointer1 = createRandomCoordinate();
        int[] pointer2;
        int[] pointer3;

        //VC - setting pointer 2 and 3 until they are all unique
        do {
            pointer2 = createRandomCoordinate();
        }
        while (Arrays.equals(pointer1, pointer2));// && something?

        do {
            pointer3 = createRandomCoordinate();
        }
        while (Arrays.equals(pointer1, pointer3)
                || Arrays.equals(pointer2, pointer3));

     /*   lines for testing purposes
        System.out.println(pointer1[0] + " , " + pointer1[1]);
        System.out.println(pointer2[0] + " , " + pointer2[1]);
        System.out.println(pointer3[0] + " , " + pointer3[1]);
        System.out.println("Next Set");

      */
        try {
            while (currentNumOfWaterTiles <= maxNumOfWaterTiles) {
                //VC - This replaces the grid position with a new water resource tile
                gridLayout[pointer1[0]][pointer1[1]] =
                        new ResourceTile(pointer1[0], pointer1[1], "water");
                //VC - adding the tile reference to a hashtable
                resourceTileHashSet.add((ResourceTile) gridLayout[pointer1[0]][pointer1[1]]);

                //VC - This replaces the grid position with a new water resource tile
                gridLayout[pointer2[0]][pointer2[1]] =
                        new ResourceTile(pointer2[0], pointer2[1], "water");
                //VC - adding the tile reference to a hashtable
                resourceTileHashSet.add((ResourceTile) gridLayout[pointer2[0]][pointer2[1]]);

                //VC - This replaces the grid position with a new water resource tile
                gridLayout[pointer3[0]][pointer3[1]] =
                        new ResourceTile(pointer3[0], pointer3[1], "water");
                //VC - adding the tile reference to a hashtable
                resourceTileHashSet.add((ResourceTile) gridLayout[pointer3[0]][pointer3[1]]);

                mutateCoordinatePointer(pointer1);
                mutateCoordinatePointer(pointer2);
                mutateCoordinatePointer(pointer3);

                currentNumOfWaterTiles++;
            }
        }
        catch(NoAvailableTilesException NTAE){

        }
    }

    /**
     * This method creates a completely random coordinate pointer in the
     * format of [row, column] to use as a seed value within the grid
     * for placing resources
     *
     * @author Vincent Capra
     */
    private int[] createRandomCoordinate(){
        Random randomNum = new Random();
        int randomColumn = randomNum.nextInt(gridSize);
        int randomRow = randomNum.nextInt(gridSize);

        return new int[] {randomRow, randomColumn};
    }

    /**
     * This method creates a mutates a coordinate pointer to an unoccupied
     * and adjacent tile. This passes an error if there are no available
     * tiles adjacent
     *
     * @author Vincent Capra
     * @param originalPointer is the pointer to the tile coordinates to
     *                        be mutated
     * @throws NoAvailableTilesException if all possible mutations result
     * in no available adjacent tiles
     */
    private int[] mutateCoordinatePointer(int[] originalPointer)
            throws NoAvailableTilesException{
        //TODO Need to implement an actual mutation method
        // Something like add 1 in 1 direction at a time until an avail tile is discovered
        if(true){
            return new int[]{0, 0};
        }
        else
            throw new NoAvailableTilesException("no tiles");
    }

    /**
     * This method replaces available terrain tiles with non-water
     * resource tiles at random positions within the map
     *
     * @author Vincent Capra
     */
    private void placeResourceTiles(){

    }

    /**
     * This method places an initial population of Humans at a random
     * and available terrain tile within the grid
     *
     * @author Vincent Capra
     * @param initial_population is the varible amount of the initial human
     *                           population. Should be limited to <50
     */
    private void seedInitialHumanPopulation(int initial_population){

    }

}