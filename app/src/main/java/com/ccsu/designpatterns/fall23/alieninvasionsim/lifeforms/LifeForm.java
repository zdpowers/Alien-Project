package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.utilities.EventListener;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

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
    protected abstract void gather();

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
    protected abstract void move();

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
}
