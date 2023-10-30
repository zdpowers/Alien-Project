package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms.LifeForm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * This holds the Tile and LifeForm data in a grid
 * to act as the "world" for the simulation.
 *
 * @author Vincent Capra
 * @version 1.0
 * @since 2023-10-26
 */
public class Grid {
    /** Length of a single side of the grid as a
     * number of elements along the same horizontal or vertical line. */
    private int mGridAxisLength;

    /** VC - A list of all Tiles in this Grid [column][row] */
    private GridTile[][] mGridLayout;
    /** A list of all LifeForms in the simulation */
    ArrayList<LifeForm> mLifeForms = new ArrayList<>();

    //VC - during the build process we can use this to track what squares
    // already are resource tiles so we don't over-write
    HashSet<ResourceTile> mResourceTileHashSet = new HashSet<ResourceTile>();

    /**
     * Constructs the grid layout based on a given
     * dimension. Then Calls subsequent methods to place water
     * and other resources on the map.
     *
     * @param gridAxisLength    The number of elements along an axis.
     *                          Total elements in this Grid = gridAxisLength^2
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-10-26
     */
    public Grid(int gridAxisLength) {
        // Initialize the grid parameters and the grid itself
        mGridAxisLength = gridAxisLength;
        mGridLayout = new GridTile[gridAxisLength][gridAxisLength];

        // VC - Initially construct the Grid with Terrain Tiles
        for (int row = 0; row < gridAxisLength; row++) {
            for (int column = 0; column < gridAxisLength; column++) {
                // Default value = TerrainTile
                mGridLayout[column][row] = new TerrainTile(column, row);
            }
        }
        // Overwrite the default tiles to place some water and resource tiles
        placeWaterTiles();
        placeResourceTiles();
    }

    /**
     * Returns the total number of tiles in this Grid.
     *
     * @return gridSize The total number of tiles in this Grid.
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-10-26
     */
    public int getGridSize() {
        return mGridAxisLength * mGridAxisLength;
    }

    /**
     * This method places connected water tiles in the map based on seed values
     * until ~30% of the map is water
     *
     * @exception NoAvailableTilesException
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-10-26
     */
    private void placeWaterTiles() {
        int maxNumOfWaterTiles = (mGridAxisLength * mGridAxisLength) / 3; // Allowed amount of water
        int currentNumOfWaterTiles = 0; // Amount of water tiles accumulator

        //VC - Creating 3 unique pointers [row_pos, column_pos]
        int[] pointer1 = createRandomCoordinate();
        int[] pointer2;
        int[] pointer3;

        //VC - setting pointer 2 and 3 until they are all unique
        do {
            pointer2 = createRandomCoordinate();
        }
        while (Arrays.equals(pointer1, pointer2));
        do {
            pointer3 = createRandomCoordinate();
        }
        while (Arrays.equals(pointer1, pointer3)
                || Arrays.equals(pointer2, pointer3));

        //// lines for testing purposes
        //System.out.println(pointer1[0] + " , " + pointer1[1]);
        //System.out.println(pointer2[0] + " , " + pointer2[1]);
        //System.out.println(pointer3[0] + " , " + pointer3[1]);
        //System.out.println("Next Set");

        try {
            while (currentNumOfWaterTiles < maxNumOfWaterTiles) {
                //VC - This replaces the grid position with a new water resource tile
                mGridLayout[pointer1[0]][pointer1[1]] =
                        new ResourceTile(pointer1[0], pointer1[1], "water");
                //VC - Adding the tile reference to a hashtable
                mResourceTileHashSet.add((ResourceTile) mGridLayout[pointer1[0]][pointer1[1]]);

                //VC - This replaces the grid position with a new water resource tile
                mGridLayout[pointer2[0]][pointer2[1]] =
                        new ResourceTile(pointer2[0], pointer2[1], "water");
                //VC - Adding the tile reference to a hashtable
                mResourceTileHashSet.add((ResourceTile) mGridLayout[pointer2[0]][pointer2[1]]);

                //VC - This replaces the grid position with a new water resource tile
                mGridLayout[pointer3[0]][pointer3[1]] =
                        new ResourceTile(pointer3[0], pointer3[1], "water");
                //VC - Adding the tile reference to a hashtable
                mResourceTileHashSet.add((ResourceTile) mGridLayout[pointer3[0]][pointer3[1]]);

                // Acquire the next tile for water resource allocation for each pointer
                mutateCoordinatePointer(pointer1);
                mutateCoordinatePointer(pointer2);
                mutateCoordinatePointer(pointer3);

                // Increase the number of water tiles by 3
                currentNumOfWaterTiles += 3;
            }
        }
        catch(NoAvailableTilesException e) {}
    }

    /**
     * This method creates a completely random coordinate pointer in the
     * format of [row, column] to use as a seed value within the grid
     * for placing resources
     *
     * @returns coord   Random coordinate within the Grid as an int[x, y]
     *
     * @author Vincent Capra
     * @version 1.1
     * @since 2023-10-29
     */
    private int[] createRandomCoordinate() {
        Random randomNum = new Random();
        return new int[] {randomNum.nextInt(mGridAxisLength), randomNum.nextInt(mGridAxisLength)};
    }

    /**
     * This method finds an unoccupied adjacent tile and returns the coordinates within this Grid.
     * Returns a NoAvailableTilesException if there are no available adjacent tiles.
     *
     * @param origin                        The origin tile coordinates as int[x, y]
     * @return                              The next available, adjacent tile's
     *                                      coordinate as int[x, y] within this Grid
     * @throws NoAvailableTilesException    If no available adjacent tile found
     *
     * @author Vincent Capra
     * @version 1.0
     * @since N/A
     */
    private int[] mutateCoordinatePointer(int[] origin)
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
     * @author N/A
     * @version 1.0
     * @since N/A
     */
    private void placeResourceTiles() {}

    /**
     * This method places an initial population
     * of LifeForms at a random location.
     *
     * @param lifeForms Array of LifeForms to be
     *                  placed in a clustered area.
     *
     * @author N/A
     * @version 1.0
     * @since N/A
     */
    private void placeLifeFormCluster(ArrayList<LifeForm> lifeForms) {}

    /**
     * Iterate through the array of all lifeforms and progress them.
     *
     *  @author Rocky Trinh
     *  @version 1.0
     *  @since 2023-29-10
     */
    public void progressLifeForms() {
        for(LifeForm i : mLifeForms) {
            i.progress(this);
        }
    }

    /**
     * NOTE: NEED TO EDIT THIS TO MAKE DEEP COPY OF THE GRID
     * Method to create memento object to save the grid's state
     *
     * @return returns a memento object representing the grid's state
     *
     * @author Zack Powers
     * @version 1.0
     * @since 2023-29-10
     */
    public GridMemento save() {
        return new GridMemento(this);
    }

    /**
     * Method to restore the grid to a previous state from a memento object
     *
     * @param state a memento object containing a grid state
     *
     * @author Zack Powers
     * @version 1.0
     * @since 2023-29-10
     */
    public void restore(GridMemento state) {
        mGridLayout = state.getGridState().mGridLayout;
    }
}