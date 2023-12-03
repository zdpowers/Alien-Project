package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.IRON;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.OIL;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.ONEUP;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.URANIUM;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.WATER;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

import java.util.Random;

/**
 * A class to define the behavior of Martian Alien
 * lifeforms within the simulation
 *
 * @author Vincent Capra, Joseph Lumpkin, Zack Powers
 */
public class Martian extends LifeForm {

    Martian(TerrainTile spawn_tile) {
        super(spawn_tile);
    }

    /**
     * Prototype constructor. This constructor is used when using the clone method to create a copy of a LifeForm object
     * @param source the source object which is to be cloned. The new object is initialized with the parameters of the source object.
     * @param residence the new TerrainTile that the LifeForm will be cloned to
     * @author Zack Powers
     * @since 2023-12-11
     */
    Martian(Martian source, TerrainTile residence) {
        super(source, residence);
    }

    @Override
    protected void reproduce() {

    }

    @Override
    protected void checkForApplicableResources() {
        boolean dontMoveFlag = false;
        for(ResourceTile neighboringResourceTile : getNeighboringResources()) {
            if (neighboringResourceTile.getResourceType() == URANIUM ||
                    neighboringResourceTile.getResourceType()== IRON ||
                    neighboringResourceTile.getResourceType() == OIL)
                dontMoveFlag = true;
        }
        if(dontMoveFlag) mine(getTileOfResidence().getTileCoordinates());
        else move();
    }

    /**
     * This is the behavior for a template pattern to extract adjacent tile resources.
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-12-1
     */
    @Override
    protected void mine(int[] current_coordinates){
        for(ResourceTile neighboringResourceTile : getNeighboringResources()){
            if (neighboringResourceTile.getResourceType() == IRON) {
                setAmountOf_Iron(getAmountOf_Iron()+1);
            }
            else if (neighboringResourceTile.getResourceType() == OIL) {
                setAmountOf_Oil(getAmountOf_Oil()+1);
            }
            else if (neighboringResourceTile.getResourceType() == URANIUM) {
                setAmountOf_Uranium(getAmountOf_Uranium()+1);
            }
        }
        System.out.println("Martian at column: " + current_coordinates[0]
                + "; and row: " + current_coordinates[1] + " has uranium: "+ getAmountOf_Uranium() +
                ", has water: " + getAmountOf_Water() +
                ", has oil: " + getAmountOf_Oil() +
                ", has iron: " + getAmountOf_Iron());
    }

    @Override
    protected void move() {

        if (getNeighboringTerrain().isEmpty()) return;
        Random rand = new Random();
        TerrainTile randomTile = null;

        while(randomTile == null) {
            int randomIndex = rand.nextInt(getNeighboringTerrain().size());
            randomTile = getNeighboringTerrain().get(randomIndex);

            // If the neighboring tile is already occupied,
            // check neighboring ones until an available one is found
            if(randomTile.getOccupant() != null){ // VC - meaning tile is occupied
                getNeighboringTerrain().remove(randomIndex);
                if (getNeighboringTerrain().isEmpty()) return; // no available tiles
                randomTile = null;
            }
            //moves this Lifeform to the new tile
            else {
                getTileOfResidence().setOccupant(null);
                randomTile.setOccupant(this);
                setTileOfResidence(randomTile);
                System.out.println("Martian moved to: " +  getTileOfResidence().getTileCoordinates().toString());
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
        return new Martian(this, residence);
    }
}
