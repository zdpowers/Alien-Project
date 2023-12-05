package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.IRON;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.OIL;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.ONEUP;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.URANIUM;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.WATER;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.NoAvailableTilesException;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class to define the behavior of Human lifeforms within the simulation
 *
 * @author Vincent Capra, Joseph Lumpkin, Zack Powers
 */
public class Human extends LifeForm {

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
     * method to possibly add another human to the tile that this
     * human currently inhabits
     *
     * @author Vincent Capra
     * @return boolean true if occupied
     */
    @Override
    protected void reproduce() {
        int temp_population =
                reproduceStrategy.reproduceStratMethod(getPopulationCount(), this);
        setPopulationCount(temp_population);
    }

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
            } catch (NoAvailableTilesException e) {}
        }
        if (coords[1] < 9) { // Tile 1 down from origin
            try {
                int[] bottomTileCoords = new int[]{coords[0], coords[1]+1}; // inverted coords
                int index = grid.getTileIndex(bottomTileCoords);
                Tile tile = grid.getTiles().get(index);
                if (tile instanceof TerrainTile) {
                    neighboringTiles.add((TerrainTile) tile);
                }
            } catch (NoAvailableTilesException e) {}
        }
        if (coords[0] > 0) { // Tile 1 left from origin
            try {
                int[] topTileCoords = new int[]{coords[0]-1, coords[1]}; // inverted coords
                int index = grid.getTileIndex(topTileCoords);
                Tile tile = grid.getTiles().get(index);
                if (tile instanceof TerrainTile) {
                    neighboringTiles.add((TerrainTile) tile);
                }
            } catch (NoAvailableTilesException e) {}
        }
        if (coords[0] < 9) { // Tile 1 right from origin
            try {
                int[] topTileCoords = new int[]{coords[0]+1, coords[1]}; // inverted coords
                int index = grid.getTileIndex(topTileCoords);
                Tile tile = grid.getTiles().get(index);
                if (tile instanceof TerrainTile) {
                    neighboringTiles.add((TerrainTile) tile);
                }
            } catch (NoAvailableTilesException e) {}
        }

        for (TerrainTile tile : neighboringTiles) {
            LifeForm lifeForm = tile.getOccupant();
            if (tile.getOccupant() != null && lifeForm instanceof Martian) {
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

    @Override
    protected void defend(int damage) {

    }

    @Override
    public LifeForm clone(TerrainTile residence) {
        return new Human(this, residence);
    }
}
