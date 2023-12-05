package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.IRON;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.OIL;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.ONEUP;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.URANIUM;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.WATER;

import android.util.Log;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.BlizzardWeatherStrategy;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.BuffDebuffTypes;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ClearWeatherStrategy;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.DroughtWeatherStrategy;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.FloodingWeatherStrategy;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.WeatherStrategy;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.WeatherContext;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.NoAvailableTilesException;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Tile;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.WeatherStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class to define the behavior of Human lifeforms within the simulation
 *
 * @author Vincent Capra, Joseph Lumpkin, Zack Powers
 */
public class Human extends LifeForm {
private ReproduceStrategy reproduceStrategy;
    Human(TerrainTile spawn_tile) {
        super(spawn_tile);
        reproduceStrategy = new HumanBaseReproductionStrat();
    }

    /**
     * Prototype constructor. This constructor is used when using the clone method to create a copy of a LifeForm object
     * @param source the source object which is to be cloned. The new object is initialized with the parameters of the source object.
     * @param residence the new TerrainTile that the LifeForm will be cloned to
     * @author Zack Powers
     * @since 2023-12-11
     */
    Human(Human source, TerrainTile residence) {
        super(source, residence);
    }

    /**
     * This is the default behavior for a template pattern to extract adjacent tile resources.
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-12-1
     */

    @Override
    protected void mine(int[] current_coordinates){
        for(ResourceTile neighboringResourceTile : getNeighboringResources()){
            //VC - if next to a one-up mushroom the human reproduction strategy will change.
            if (neighboringResourceTile.getResourceType() == ONEUP) {
                reproduceStrategy = new OneUpReproductionStrat();
            }
        }
/*        System.out.println("Human at column: " + current_coordinates[0]
                + "; and row: " + current_coordinates[1] + " has uranium: "+ getAmountOf_Uranium() +
                ", has oil: " + getAmountOf_Oil() +
                ", has iron: " + getAmountOf_Iron());*/
    }
    /**
     * method to possibly call the appropriate Reproduce Strategy and set the population of the
     * Human at this tile.
     *
     * @author Vincent Capra
     * @since 2023-12-1
     */
    @Override
    protected void reproduce() {
        int temp_population =
                reproduceStrategy.reproduceStratMethod(getPopulationCount(), this);
        setPopulationCount(temp_population);
    }

    /**
     * Template method that checks if a Human is neighboring a resource that it cares about and
     * stays put if so, moves if not.
     *
     * @author Vincent Capra
     * @since 2023-12-3
     */
    @Override
    protected void checkForApplicableResources() {
        boolean dontMoveFlag = false;
        for(ResourceTile neighboringResourceTile : getNeighboringResources()) {
            if (neighboringResourceTile.getResourceType() == ONEUP)
                dontMoveFlag = true;
        }
        if(dontMoveFlag) mine(getTileOfResidence().getTileCoordinates());
        else move();
    }
    /**
     * Moves a human to an available, neighboring, unoccupied, random terrain tile.
     *
     * @author Vincent Capra, Joseph Lumpkin
     * @since 2023-12-3
     */
    @Override
    protected void move() {
        List<TerrainTile> temp_neighboring_tiles = getNeighboringTerrain();
        if (temp_neighboring_tiles.isEmpty()) {
            setHaveNeighboringTiles(false);
            return; // if there are no tiles to move to
        }

        //check all neighboring tiles for an Alien, if found, don't move
        for(TerrainTile check_tile : temp_neighboring_tiles){
            if(check_tile.getOccupant() instanceof Martian){
                setHaveNeighboringTiles(false);
                return;
            }
        }

        Random rand = new Random();
        TerrainTile randomTile = null;

        while(randomTile == null) {
            //pick a random available neighboring tile
            int randomIndex = rand.nextInt(getNeighboringTerrain().size());
            randomTile = getNeighboringTerrain().get(randomIndex);

            //if tile is unavailable remove it from the list we are checking
            if(randomTile.getOccupant() != null){
                getNeighboringTerrain().remove(randomIndex);
                if (getNeighboringTerrain().isEmpty()){
                    setHaveNeighboringTiles(false);
                    return;
                }

                randomTile = null;
            }
            //moves this Lifeform to the new tile
            else {
                getTileOfResidence().setOccupant(null);
                randomTile.setOccupant(this);
                setTileOfResidence(randomTile);
                System.out.println("Human moved to: " +  getTileOfResidence().getTileCoordinates().toString());
            }
        }
        //VC - somewhere in here need to set after have moved to a new tile
        setHaveNeighboringTiles(false);
        //setNeighboringTerrain(null);
        //setNeighboringResources(null);
    }

    /**
     * Humans can attack neighboring Martians. If they entirely deplete their population,
     * removes Martian Object from the Grid. If Human is entirely depleted of their population,
     * they are removed from the Grid. There was a bug in the
     * LifeForm.getNeighboringTerrainTileReferences(), so for the sake of a functional project ,
     * code was redundantly implemented here.
     *
     * @param grid the input grid which is
     * @author  Joseph Lumpkin
     * @since 2023-12-4
     */


    private WeatherContext mWeatherContext;
    
    @Override
    protected void attack(Grid grid) {
//        List<TerrainTile> neighboringTiles = getNeighboringTerrain();
        ArrayList<TerrainTile> neighboringTiles = new ArrayList<>();
        int[] coords = getTileOfResidence().getTileCoordinates();
        if (coords[1] > 0) { // Tile 1 up from origin
            try {
                int[] topTileCoords = new int[]{coords[0], coords[1]-1}; // inverted coords
                int index = grid.getTileIndex(topTileCoords);
                Tile tile = grid.getTiles().get(index);
                if (tile instanceof TerrainTile) {
                    neighboringTiles.add((TerrainTile) tile);
                }
            } catch (NoAvailableTilesException e) {
                // No handling, just logging and proceeding
                Log.e("Grid", e.getMessage());
            }
        }
        if (coords[1] < 9) { // Tile 1 down from origin
            try {
                int[] bottomTileCoords = new int[]{coords[0], coords[1]+1}; // inverted coords
                int index = grid.getTileIndex(bottomTileCoords);
                Tile tile = grid.getTiles().get(index);
                if (tile instanceof TerrainTile) {
                    neighboringTiles.add((TerrainTile) tile);
                }
            } catch (NoAvailableTilesException e) {
                // No handling, just logging and proceeding
                Log.e("Grid", e.getMessage());
            }
        }
        if (coords[0] > 0) { // Tile 1 left from origin
            try {
                int[] topTileCoords = new int[]{coords[0]-1, coords[1]}; // inverted coords
                int index = grid.getTileIndex(topTileCoords);
                Tile tile = grid.getTiles().get(index);
                if (tile instanceof TerrainTile) {
                    neighboringTiles.add((TerrainTile) tile);
                }
            } catch (NoAvailableTilesException e) {
                // No handling, just logging and proceeding
                Log.e("Grid", e.getMessage());
            }
        }
        if (coords[0] < 9) { // Tile 1 right from origin
            try {
                int[] topTileCoords = new int[]{coords[0]+1, coords[1]}; // inverted coords
                int index = grid.getTileIndex(topTileCoords);
                Tile tile = grid.getTiles().get(index);
                if (tile instanceof TerrainTile) {
                    neighboringTiles.add((TerrainTile) tile);
                }
            } catch (NoAvailableTilesException e) {
                // No handling, just logging and proceeding
                Log.e("Grid", e.getMessage());
            }
        }

        for (TerrainTile tile : neighboringTiles) {
            LifeForm lifeForm = tile.getOccupant();
            if (tile.getOccupant() != null && lifeForm instanceof Martian) {
                // Get the current weather strategy from the weather context
                // Create a WeatherContext object with a FloodingWeatherStrategy and assign it to a variable
                WeatherContext weatherContext = new WeatherContext();
                // Use the weather context to get the weather strategy
                WeatherStrategy weatherStrategy = weatherContext.getWeather();
                // Check if it is a drought weather strategy
                if (weatherStrategy instanceof DroughtWeatherStrategy) {
                    // Increase the attack damage by x amount,
                    int newAlienPopulation = lifeForm.getPopulationCount() - 4; // Change the attack damage from 2 to 4
                    if (newAlienPopulation <= 0) {
                        lifeForm.setPopulationCount(newAlienPopulation);
                        return;
                    } else {
                        lifeForm.setPopulationCount(newAlienPopulation);
                        setPopulationCount(getPopulationCount() - 3);
                    }
                    // Check for Flooding weather strategy
                } else if (weatherStrategy instanceof BlizzardWeatherStrategy) {
                    int newAlienPopulation = lifeForm.getPopulationCount() - 1; //Change the attack damage from 2 to 1
                    if (newAlienPopulation <= 0) {
                        lifeForm.setPopulationCount(newAlienPopulation);
                        return;
                    } else {
                        lifeForm.setPopulationCount(newAlienPopulation);
                        setPopulationCount(getPopulationCount() - 3);
                    }
                } else {
                    // Use the normal attack damage
                    int newAlienPopulation = lifeForm.getPopulationCount() - 2;
                    if (newAlienPopulation <= 0) {
                        lifeForm.setPopulationCount(newAlienPopulation);
                        return;
                    } else {
                        lifeForm.setPopulationCount(newAlienPopulation);
                        setPopulationCount(getPopulationCount() - 3);
                    }
                }
            }
        }
    }

    @Override
    protected void defend(int damage) {

    }



    @Override
    public LifeForm clone(TerrainTile residence) {
        return new Human(this, residence);
    }
}
