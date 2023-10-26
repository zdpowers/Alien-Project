package com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms;

/**
 * A class to create a generic lifeform within the simulation
 *
 * @author Vincent Capra
 */

public abstract class Lifeform {

    private int amountOf_Water, amountOf_Uranium, amountOf_Oil, amountOf_Iron;
    private static int technicalRating, movementRating,
            reproductionRating, miningRating;
    public Lifeform(){

    }
    /**
     * A method to gather resources for the lifeform to add
     * to their species' total
     *
     * @author Vincent Capra
     */
    private void mineResources(){


    }
    /**
     * A method to create more objects via reproduction
     *
     * @author Vincent Capra
     */
    private void reproduce(){

    }

    protected abstract void move();

}
