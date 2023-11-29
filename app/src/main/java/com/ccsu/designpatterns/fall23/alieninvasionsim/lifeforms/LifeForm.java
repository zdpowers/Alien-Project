package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.NoAvailableTilesException;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile;
import com.ccsu.designpatterns.fall23.alieninvasionsim.utilities.EventListener;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class to create a generic life form within the simulation
 *
 * @author Vincent Capra, Joseph Lumpkin, Zack Powers
 */

public abstract class LifeForm
        implements ProgressibleLifeForm, EventListener {

    /** Resource amounts held by this life form. */
    private int amountOf_Water = 0;
    private int amountOf_Uranium = 0;
    private int amountOf_Oil = 0;
    private int amountOf_Iron = 0;

    /** Rating modifiers for this life form. */
    private static int defenseRating;   // Amount to reduce incoming damage by
    private static int attackRating;    // Amount of true (unmodified) damage to attack with
    private static int negotiationRating;   // How proficient this LifeForm is in negotiating
    private static int technicalRating;
    private static int movementRating;
    private static int reproductionRating;
    private static int miningRating;

    private TerrainTile tileOfResidence;
    private List<TerrainTile> neighboringTerrain;
    private List<ResourceTile> neighboringResources;
    private boolean haveNeighboringTiles = false;

    private int populationCount= 1;

    /**
     * Constructor.
     * @param spawn_tile the tile in which the object is placed.
     */
    public LifeForm(TerrainTile spawn_tile){
        tileOfResidence = spawn_tile;
    }

    public int getPopulationCount(){
        return populationCount;
    }
    public void setPopulationCount(int populationIncrement){
        populationCount += populationIncrement;
    }

    public TerrainTile getTileOfResidence() {
        return tileOfResidence;
    }
    public void setTileOfResidence(TerrainTile inputTile){
        tileOfResidence = inputTile;
    }

    /**
     * Prototype constructor. This constructor is used when using the clone method to create a copy of a LifeForm object
     * @param source the source object which is to be cloned. The new object is initialized with the parameters of the source object.
     * @author Zack Powers
     * @version 1.0
     * @since 2023-12-11
     */
    public LifeForm(LifeForm source) {
        this.tileOfResidence = source.tileOfResidence;
        this.amountOf_Water = source.amountOf_Water;
        this.amountOf_Uranium = source.amountOf_Uranium;
        this.amountOf_Oil = source.amountOf_Oil;
        this.amountOf_Iron = source.amountOf_Iron;
    }

    @Override
    public final void progress(Grid grid) {
        gather();
        reproduce();
        //TODO do lifeforms move? or just reproduce into new tiles?
        move();
        attack(grid);
    }

    /**
     * If this is a composite component return it's composite interface.
     * Leaving for now as possible expansion if we decide later to expand the
     * tree structure of the LifeForm sub-classes.
     *
     * @return Composite interface for this class if it is a composite
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-26-10
     */
    public LifeFormComposite composite() {
        return null;
    }

    /**
     * A method to gather resources for this LifeForm.
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-26-10
     */
    protected void gather() {

        int[] currentCoordinates = tileOfResidence.getTileCoordinates();
        String adjoining_resources;

        //VC - This gets/sets all neighboring tiles. It's called on initial loop and then again
        // if and when the lifeform moves to a new terrain tile.
        if(!haveNeighboringTiles) {
            try {
                neighboringTerrain =
                        getNeighboringTerrainTileReferences(currentCoordinates);
            } catch (NoAvailableTilesException NAT) {
                System.out.println("Some neighboring tiles may not be available");
            }

            try {
                neighboringResources =
                        getNeighboringResourceTileReferences(currentCoordinates);
            } catch (NoAvailableTilesException NAT) {
                System.out.println("Some neighboring tiles may not be available");
            }
            haveNeighboringTiles = true;
            System.out.println("Life at column: " + currentCoordinates[0]
                    + "; and row: " + currentCoordinates[1] + " is next to terrain blocks "
                    + neighboringTerrain.toString() + " and resources "  +
                    neighboringResources.toString());
        }

        if(neighboringResources == null)
            move();
        else{
            for(ResourceTile neighboringResourceTile : neighboringResources){
                if (neighboringResourceTile.getResourceType().equals("water"))
                    amountOf_Water += 1;
                else if (neighboringResourceTile.getResourceType().equals("iron")) {
                    amountOf_Iron += 1;
                }
                else if (neighboringResourceTile.getResourceType().equals("oil")) {
                    amountOf_Oil += 1;
                }
                else if (neighboringResourceTile.getResourceType().equals("uranium")) {
                    amountOf_Uranium += 1;
                }
            }
            System.out.println("Life at column: " + currentCoordinates[0]
                    + "; and row: " + currentCoordinates[1] + " has uranium: " + amountOf_Uranium +
                    " has water: " + amountOf_Water +
                    " has oil: " + amountOf_Oil +
                    " has iron: " + amountOf_Iron);
        }
    }

    /**
     * A method to create more LifeForms via reproduction.
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-26-10
     */
    protected abstract void reproduce();

    /**
     * A method to move this LifeForm.
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-26-10
     */
    protected void move(){
        //VC - somewhere in here need to set after have moved to a new tile
        haveNeighboringTiles = false;
    };

    /**
     * A method to attack with this LifeForm.
     *
     * @author Joseph Lumpkin
     * @version 1.0
     * @since 2023-26-10
     */
    protected abstract void attack(Grid grid);

    /**
     * A method to defend from damage with this LifeForm.
     *
     * @param damage    Amount of true (unmodified) damage
     *                  to attempt to deal to this LifeForm.
     *
     * @author Joseph Lumpkin
     * @version 1.0
     * @since 2023-26-10
     */
    protected abstract void defend(int damage);

    /**
     * Abstract clone method for the prototype creational pattern.
     * Called to create a copy of lifeForm object to populate neighboring tiles.
     * @return a copy of the LifeForm object that the method is called on.
     * @author Zack Powers
     * @version 1.0
     * @since 2023-12-11
     */
    public abstract LifeForm clone();

    @Override
    public void update(String data) {}

    /**
     * Method takes an input coordinate for this tile as a [col, row] array and returns
     * references to all neighboring Terrain Tile Objects
     * @return List of neighboring Terrain Tiles
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-11-28
     */
    private List<TerrainTile> getNeighboringTerrainTileReferences(int[] inputCoordinates) throws NoAvailableTilesException {
        List<TerrainTile> returnTileReferences = new ArrayList<>();
        List<int[]> neighborTiles =
                Grid.getInstance(10).getNeighboringTerrainTiles(inputCoordinates);
        Iterator<int[]> tileIterator = neighborTiles.listIterator();

        //VC - Loops over all surrounding tiles to check if a Terrain Tile and adds reference to Array List
        while(tileIterator.hasNext()) {
            int[] currentTileCoordinates = tileIterator.next();
            int index = Grid.getInstance(10).getTileIndex(currentTileCoordinates);
            returnTileReferences.add((TerrainTile) Grid.getInstance(10).getTiles().get(index));

        }
        return returnTileReferences;

    }

    /**
     * Method takes an input coordinate for this tile as a [col, row] array and returns
     * references to all neighboring Resource Tile Objects
     * @return List of neighboring Resource Tiles
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-11-28
     */
    private List<ResourceTile> getNeighboringResourceTileReferences(int[] inputCoordinates) throws NoAvailableTilesException {
        List<ResourceTile> returnTileReferences = new ArrayList<>();
        List<int[]> neighborTiles =
                Grid.getInstance(10).getNeighboringResourceTiles(inputCoordinates);
        Iterator<int[]> tileIterator = neighborTiles.listIterator();

        //VC - Loops over all surrounding tiles to check if a Resource Tile and adds reference to Array List
        while(tileIterator.hasNext()) {
            int[] currentTileCoordinates = tileIterator.next();
            int index = Grid.getInstance(10).getTileIndex(currentTileCoordinates);
            if(Grid.getInstance(10).getTiles().get(index) instanceof ResourceTile){
                returnTileReferences.add((ResourceTile) Grid.getInstance(10).getTiles().get(index));
            }
        }
        return returnTileReferences;
    }
}
