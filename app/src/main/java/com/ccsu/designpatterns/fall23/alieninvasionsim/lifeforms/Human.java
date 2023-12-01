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

/*    @Override
    protected void gather() {
        int[] currentCoordinates = super.getTileOfResidence().getTileCoordinates();

    }*/

    /**
     * This is the default behavior for a template pattern to extract adjacent tile resources.
     * This will be overridden in the Human Subclass implementation
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-12-1
     */

    @Override
    protected void mine(int[] current_coordinates){
        for(ResourceTile neighboringResourceTile : getNeighboringResources()){
            if (neighboringResourceTile.getResourceType() == WATER)
                setAmountOf_Water(getAmountOf_Water()+1);
            else if (neighboringResourceTile.getResourceType() == IRON) {
                setAmountOf_Iron(getAmountOf_Iron()+1);
            }
            else if (neighboringResourceTile.getResourceType() == OIL) {
                setAmountOf_Oil(getAmountOf_Oil()+1);
            }
            else if (neighboringResourceTile.getResourceType() == URANIUM) {
                setAmountOf_Uranium(getAmountOf_Uranium()+1);
            }
            //VC - if next to a one-up mushroom the human reproduction strategy will change.
            else if (neighboringResourceTile.getResourceType() == ONEUP) {
                reproduceStrategy = new OneUpReproductionStrat();
            }
        }
        System.out.println("Human at column: " + current_coordinates[0]
                + "; and row: " + current_coordinates[1] + " has uranium: "+ getAmountOf_Uranium() +
                ", has water: " + getAmountOf_Water() +
                ", has oil: " + getAmountOf_Oil() +
                ", has iron: " + getAmountOf_Iron());
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
                reproduceStrategy.reproduceStratMethod(getPopulationCount());
        setPopulationCount(temp_population);
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
