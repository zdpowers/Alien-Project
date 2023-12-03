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
        if (temp_neighboring_tiles.isEmpty()) return; // if there are no tiles to move to

        //check all neighboring tiles for an Alien, if found, don't move
        for(TerrainTile check_tile : temp_neighboring_tiles){
            if(check_tile.getOccupant() instanceof Martian){
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
                if (getNeighboringTerrain().isEmpty()) return;
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

    }

    @Override
    protected void defend(int damage) {

    }

    @Override
    public LifeForm clone(TerrainTile residence) {
        return new Human(this, residence);
    }
}
