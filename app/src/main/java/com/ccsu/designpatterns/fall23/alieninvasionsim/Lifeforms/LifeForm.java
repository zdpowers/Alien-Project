package com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.Grid;

/**
 * A class to create a generic life form within the simulation
 *
 * @author Vincent Capra
 */

public abstract class LifeForm
        implements ProgressibleLifeform {

    /** Resource amounts held by this life form. */
    private int amountOf_Water;
    private int amountOf_Uranium;
    private int amountOf_Oil;
    private int amountOf_Iron;

    /** Rating modifiers for this life form. */
    private static int defenseRating;   // Amount to reduce incoming damage by
    private static int attackRating;    // Amount of true (unmodified) damage to attack with
    private static int negotiationRating;   // How proficient this LifeForm is in negotiating
    private static int technicalRating;
    private static int movementRating;
    private static int reproductionRating;
    private static int miningRating;

    protected LifeForm() {}

    @Override
    public final void progress(Grid grid) {
        gather();
        reproduce();
        //TODO do lifeforms move? or just reproduce into new tiles?
        move();
        attack(grid);
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

}
