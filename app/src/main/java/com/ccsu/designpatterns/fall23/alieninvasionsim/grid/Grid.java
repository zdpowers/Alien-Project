package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import android.util.Log;

import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.Human;
import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeFormFactory;
import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.Martian;
import com.ccsu.designpatterns.fall23.alieninvasionsim.utilities.EventManager;
import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This holds the Tile and LifeForm data in a grid
 * to act as the "world" for the simulation.
 *
 * @author Vincent Capra, Joseph Lumpkin, Zack Powers
 * @version 1.0
 * @since 2023-10-26
 */
public class Grid {
    /**
     * Length of a single side of the grid as a
     * number of elements along the same horizontal or vertical line.
     */
    private int mGridAxisLength;
    /**
     * A list of all LifeForms in the grid
     */
    private List<LifeForm> mLifeForms = new ArrayList<>();
    /**
     * A list of all tiles in the grid
     */
    private List<Tile> mTiles = new ArrayList<>();
    /**
     * VC - the instance variable for Singleton implementation
     */
    private static Grid instance;

    /**
     * An instance of EventManager which is used to maintain and update EventListeners
     */
    private EventManager manager = new EventManager();
    /**
     * An instance of GridCaretaker, which is used to store GridMementos
     */
    private GridCaretaker gridCaretaker = new GridCaretaker();

    /**
     * Constructs the grid layout based on a given
     * dimension. Then Calls subsequent methods to place water
     * and other resources on the map.
     *
     * @param gridAxisLength The number of elements along an axis.
     *                       Total elements in this Grid = gridAxisLength^2
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-10-26
     */
    public Grid(int gridAxisLength) {
        // Initialize the grid parameters and the grid itself
        mGridAxisLength = gridAxisLength;

        // VC - Initially construct the Grid with Terrain Tiles
        for (int row = 0; row < gridAxisLength; row++) {
            for (int column = 0; column < gridAxisLength; column++) {
                // Default value = TerrainTile
                mTiles.add(new TerrainTile(column, row));
            }
        }
        // Overwrite the default tiles to place some water and resource tiles
        placeWaterTiles();
        placeResourceTiles();
        placeLifeFormCluster();
    }

    /**
     * Implements the Singleton pattern for the Grid class
     *
     * @return a new Grid instance.
     * @param gridAxisLength is the private constructor parameter
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-11-11
     */

    public static Grid getInstance(int gridAxisLength){

        //VC - Adding this for robustness, should handle any threading issues
        // in the event that we need to add them later.
        //synchronized (Grid.class) {
            if (instance == null) {
                instance = new Grid(gridAxisLength);
            }
        //}
        return instance;
    }

    /**
     * Returns the total number of tiles in this Grid.
     *
     * @return gridSize The total number of tiles in this Grid.
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
     * @throws NoAvailableTilesException
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
        while (Arrays.equals(pointer1, pointer3) || Arrays.equals(pointer2, pointer3));


        try {
            mTiles.set(getTileIndex(pointer1), new ResourceTile(pointer1[0], pointer1[1], "water"));
            currentNumOfWaterTiles += 1;
        } catch (NoAvailableTilesException e) {
            Log.e("Grid", e.getMessage());
        }
        try {
            mTiles.set(getTileIndex(pointer2), new ResourceTile(pointer2[0], pointer2[1], "water"));
            currentNumOfWaterTiles += 1;
        } catch (NoAvailableTilesException e) {
            Log.e("Grid", e.getMessage());
        }
        try {
            mTiles.set(getTileIndex(pointer3), new ResourceTile(pointer3[0], pointer3[1], "water"));
            currentNumOfWaterTiles += 1;
        } catch (NoAvailableTilesException e) {
            Log.e("Grid", e.getMessage());
        }


        while (currentNumOfWaterTiles < maxNumOfWaterTiles) {
            // VC - Acquire the next tile for water resource allocation for each pointer
            // If a given pointer is out of mutations(IE surrounded in a corner) is throws an
            // an exception which sets no tiles available. If it happens to all 3 pointers
            // before 30% of the map is water, we currently don't support a handling of that
            try {
                pointer1 = mutateCoordinatePointer(pointer1);
            } catch (NoAvailableTilesException e) {
                Log.e("Grid", e.getMessage());
            }
            try {
                mTiles.set(getTileIndex(pointer1), new ResourceTile(pointer1[0], pointer1[1], "water"));
                currentNumOfWaterTiles += 1;
            } catch (NoAvailableTilesException e) {
                Log.e("Grid", e.getMessage());
            }

            try {
                pointer2 = mutateCoordinatePointer(pointer2);
            } catch (NoAvailableTilesException e) {
                Log.e("Grid", e.getMessage());
            }
            try {
                mTiles.set(getTileIndex(pointer2), new ResourceTile(pointer2[0], pointer2[1], "water"));
                currentNumOfWaterTiles += 1;
            } catch (NoAvailableTilesException e) {
                Log.e("Grid", e.getMessage());
            }

            try {
                pointer3 = mutateCoordinatePointer(pointer3);
            } catch (NoAvailableTilesException e) {
                Log.e("Grid", e.getMessage());
            }
            try {
                mTiles.set(getTileIndex(pointer3), new ResourceTile(pointer3[0], pointer3[1], "water"));
                currentNumOfWaterTiles += 1;
            } catch (NoAvailableTilesException e) {
                Log.e("Grid", e.getMessage());
            }
        }
    }

    /**
     * This method creates a completely random coordinate pointer in the
     * format of [column, row] to use as a seed value within the grid
     * for placing resources
     *
     * @returns coord   Random coordinate within the Grid as an int[x, y]
     * @author Vincent Capra
     * @version 1.1
     * @since 2023-10-29
     */
    private int[] createRandomCoordinate() {
        Random randomNum = new Random();
        return new int[]{randomNum.nextInt(mGridAxisLength), randomNum.nextInt(mGridAxisLength)};
    }

    /**
     * This method finds an unoccupied adjacent tile and returns the coordinates within this Grid.
     * Returns a NoAvailableTilesException if there are no available adjacent tiles.
     *
     * @param origin The origin tile coordinates as int[x, y]
     * @return The next available, adjacent tile's
     * coordinate as int[x, y] within this Grid
     * @throws NoAvailableTilesException If no available adjacent tile found
     * @author Vincent Capra
     * @version 1.1
     * @since 2023-11-2
     */
    private int[] mutateCoordinatePointer(int[] origin) throws NoAvailableTilesException {
        ArrayList<int[]> permutations = new ArrayList<>();

        // Check above this tile
        try {
            int index = getTileIndex(new int[]{origin[0], origin[1] - 1});
            if (mTiles.get(index) instanceof TerrainTile) {
                permutations.add(new int[]{origin[0], origin[1] - 1});
            }
        } catch (NoAvailableTilesException e) {
            Log.e("Grid", e.getMessage());
        } // Index most likely out of bounds

        // Check to the right of this tile
        try {
            int index = getTileIndex(new int[]{origin[0] + 1, origin[1]});
            if (mTiles.get(index) instanceof TerrainTile) {
                permutations.add(new int[]{origin[0] + 1, origin[1]});
            }
        } catch (NoAvailableTilesException e) {
            Log.e("Grid", e.getMessage());
        } // Index most likely out of bounds

        // Check below this tile
        try {
            int index = getTileIndex(new int[]{origin[0], origin[1] + 1});
            if (mTiles.get(index) instanceof TerrainTile) {
                permutations.add(new int[]{origin[0], origin[1] + 1});
            }
        } catch (NoAvailableTilesException e) {
            Log.e("Grid", e.getMessage());
        } // Index most likely out of bounds

        // Check to the left of this tile
        try {
            int index = getTileIndex(new int[]{origin[0] - 1, origin[1]});
            if (mTiles.get(index) instanceof TerrainTile) {
                permutations.add(new int[]{origin[0] - 1, origin[1]});
            }
        } catch (NoAvailableTilesException e) {
            Log.e("Grid", e.getMessage());
        } // Index most likely out of bounds

        // Choose a random result
        if (!permutations.isEmpty()) { // Check that arraylist is not null
            Random random = new Random();
            return permutations.get(random.nextInt(permutations.size()));
        } else
            throw new NoAvailableTilesException("No available tiles found.");
    }

    /**
     * This method replaces available terrain tiles with non-water
     * resource tiles at random positions within the map
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-11-2
     */
    private void placeResourceTiles() {
        String[] resources = {"uranium", "iron", "oil"};
        for (String resource : resources) {
            int resourceTileCount = 0;
            //VC - This loop places 4 tiles per resource type on the grid
            while (resourceTileCount < 4) {
                int[] coord = createRandomCoordinate(); // Coordinate to check
                try {
                    int index = getTileIndex(coord);
                    // If this tile is not already a ResourceTile
                    if (!(mTiles.get(index) instanceof ResourceTile)) {
                        mTiles.set(index, new ResourceTile(coord[0], coord[1], resource));
                        resourceTileCount++;
                    }
                } catch (NoAvailableTilesException e) {
                    Log.e("Grid", e.getMessage());
                }
            }
        }
    }


    /**
     * This method places an initial population
     * of LifeForms at a random location.
     *
     * @author Joseph Lumpkin
     * @version 1.0
     * @since 2023-11-16
     */
    private void placeLifeFormCluster() {
        int index = 0;
        LifeFormFactory lff = new LifeFormFactory();
        int humanTileCount = 0, alienTileCount = 0;
        Tile temp_tile;
        int[] coord;
        //VC - This loop places 4 tiles per resource type on the grid
        while (humanTileCount < 5) {
            coord = createRandomCoordinate(); // Coordinate to check
            try {
                index = getTileIndex(coord);
                temp_tile = mTiles.get(index);
                // If this tile is terrain and unoccupied
                if (temp_tile instanceof TerrainTile &&
                        !((TerrainTile) temp_tile).tileIsOccupied()) {
                    temp_tile.setOccupant(lff.makeLifeForm(Human.class.toString(),(TerrainTile) temp_tile));
                    mLifeForms.add(temp_tile.getOccupant());

                    humanTileCount++;
                }
            } catch (NoAvailableTilesException e) {
                Log.e("Grid", e.getMessage());
            }
        }

        while (alienTileCount < 3) {
            coord = createRandomCoordinate(); // Coordinate to check
            try {
                index = getTileIndex(coord);
                temp_tile = mTiles.get(index);
                // If this tile is not already a ResourceTile
                if (temp_tile instanceof TerrainTile &&
                        !((TerrainTile) temp_tile).tileIsOccupied()) {
                    temp_tile.setOccupant(lff.makeLifeForm(Martian.class.toString(),(TerrainTile) temp_tile));
                    mLifeForms.add(temp_tile.getOccupant());

                    alienTileCount++;
                }
            } catch (NoAvailableTilesException e) {
                Log.e("Grid", e.getMessage());
            }
        }
    }

    /**
     * Iterate through the array of all lifeforms and progress them.
     *
     * @author Rocky Trinh
     * @version 1.0
     * @since 2023-29-10
     */
    public void progressLifeForms() {
        for (LifeForm i : mLifeForms) {
            i.progress(this);
        }
    }

    /**
     * NOTE: NEED TO EDIT THIS TO MAKE DEEP COPY OF THE GRID
     * Method to create memento object to save the grid's state
     *
     * @return returns a memento object representing the grid's state
     * @author Zack Powers
     * @version 1.0
     * @since 2023-29-10
     */
    public GridMemento save() {
        GridMemento state = new GridMemento((ArrayList) mTiles);
        gridCaretaker.add(state);
        return state;
    }

    /**
     * Method to restore the grid to a previous state from a memento object
     *
     * @param state a memento object containing a grid state
     * @author Zack Powers
     * @version 1.0
     * @since 2023-29-10
     */
    public void restore(GridMemento state) {
        mTiles = state.getGridState();
    }

    /**
     * Retrieve a tile index within mTiles with a given coordinate on the grid.
     *
     * @param coords Coordinates of the tile index to retrieve as (x, y).
     * @return The index where the Tile can be found in this Grid's mTiles.
     * @throws NoAvailableTilesException If a tile is out of bounds and not found.
     * @author Joseph Lumpkin
     * @version 1.0
     * @since 2023-11-2
     */
    private int getTileIndex(int[] coords) throws NoAvailableTilesException {
        // Check for invalid coordinates
        if (coords[0] < 0 || coords[1] < 0 || // Negative value coordinates
                coords[0] >= mGridAxisLength || coords[1] >= mGridAxisLength) { // Exceeding grid bounds
            throw new NoAvailableTilesException("No tile found. Invalid coordinates.");
        }
        if (coords[0] == 0 && coords[1] == 0) {
            return 0;
        } else {
            // y * mGridAxisLength, add in the x value, and subtract 1 because arrays start at 0
            return (coords[1] * mGridAxisLength) + coords[0] - 1;
        }
    }

    public ArrayList<Tile> getTiles() {
        return (ArrayList) mTiles;
    }
    /**
     * Uses the GridCell class to apply and get the buff and debuff values
     *
     * @author Rocky Trinh
     * @version 1.0
     * @since 2023-12-11
     */
    public void getBuffs(String[] args) {
        GridCell cell = new GridCell();

        //Apply the buffs/debuffs
        cell.applyBuffDebuff(BuffDebuffTypes.ATTACK_BUFF, 2);
        cell.applyBuffDebuff(BuffDebuffTypes.SPEED_BUFF, 2);
        cell.applyBuffDebuff(BuffDebuffTypes.DEFENSE_BUFF, 2);
        cell.applyBuffDebuff(BuffDebuffTypes.HP_BUFF, 1);
        cell.applyBuffDebuff(BuffDebuffTypes.HP_DEBUFF, -1);
        cell.applyBuffDebuff(BuffDebuffTypes.DEFENSE_DEBUFF, -1);
        cell.applyBuffDebuff(BuffDebuffTypes.SPEED_DEBUFF, -1);
        cell.applyBuffDebuff(BuffDebuffTypes.ATTACK_DEBUFF, -1);

        //Get value(s)
        int attackBuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.ATTACK_BUFF);
        int speedBuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.SPEED_BUFF);
        int defenseBuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.DEFENSE_BUFF);
        int hpBuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.HP_BUFF);
        int attackDebuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.ATTACK_DEBUFF);
        int speedDebuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.SPEED_DEBUFF);
        int defenseDebuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.DEFENSE_DEBUFF);
        int hpDebuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.HP_DEBUFF);

        //display new stats to logger (not sure if we want to this)
/*      System.out.println("Attack Buff Value: " + attackBuffValue);
        System.out.println("Defense Debuff value: " + defenseDebuffValue);*/

        //Remove buffs
        cell.removeBuffDebuff(BuffDebuffTypes.ATTACK_BUFF);
        cell.removeBuffDebuff(BuffDebuffTypes.SPEED_BUFF);
        cell.removeBuffDebuff(BuffDebuffTypes.DEFENSE_BUFF);
        cell.removeBuffDebuff(BuffDebuffTypes.HP_BUFF);
        cell.removeBuffDebuff(BuffDebuffTypes.ATTACK_DEBUFF);
        cell.removeBuffDebuff(BuffDebuffTypes.SPEED_DEBUFF);
        cell.removeBuffDebuff(BuffDebuffTypes.DEFENSE_DEBUFF);
        cell.removeBuffDebuff(BuffDebuffTypes.HP_DEBUFF);

        //Get values after removal
        attackBuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.ATTACK_BUFF);
        speedBuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.SPEED_BUFF);
        defenseBuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.DEFENSE_BUFF);
        hpBuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.HP_BUFF);
        attackDebuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.ATTACK_DEBUFF);
        speedBuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.SPEED_DEBUFF);
        defenseDebuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.DEFENSE_DEBUFF);
        hpDebuffValue = cell.getBuffDebuffValue(BuffDebuffTypes.HP_DEBUFF);

/*      System.out.println("Attack Buff Value after removal: " + attackBuffValue);
        System.out.println("Defense Debuff Value after removal: " + defenseDebuffValue);*/
    }

    /**
     * Nested Static Grid memento class
     * @author Zack Powers
     * @version 1.1
     * @since 2023-29-10
     */
    public static class GridMemento {
        private List<Tile> gridState;

        /**
         * Constructor
         * @param gridState the state of the grid that is to be saved in the snapshot
         * @author Zack Powers
         * @since 2023-29-10
         */
        private GridMemento(List<Tile> gridState) {
            this.gridState = gridState;
        }

        /**
         * Method to get the grid state.
         * @return returns the grid state as 2D array
         * @author Zack Powers
         * @since 2023-29-10
         */
        private List<Tile> getGridState() {
            return gridState;
        }
    }
}

