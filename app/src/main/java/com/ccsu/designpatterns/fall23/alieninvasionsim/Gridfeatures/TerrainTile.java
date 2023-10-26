package com.ccsu.designpatterns.fall23.alieninvasionsim.Gridfeatures;

/**
 * A class to build single terrain tiles. These are tiles that can be
 * occupied by a single country of humans or a single race of aliens
 *
 * @author Vincent Capra
 */
public class TerrainTile extends GridTile{
    private int[] occupants; //VC - need to change to an array of generic occupants

    public TerrainTile(int x_coord, int y_coord) {
        super(x_coord, y_coord);

        //need to place the tile on the board
    }

    /**
     * method to indicate if the tile is occupied
     *
     * @author Vincent Capra
     * @return boolean true if occupied
     */
    public boolean tileIsOccupied(){
        if (occupants == null)
            return false;
        return true;
    }

    /**
     * method to indicate which type of occupants are in the tile
     * This is probably a great use of the generic for next sprint!!!
     *
     * @author Vincent Capra
     * @return boolean true if occupied
     */
    public int typeOfOccupants(){ //VC - need to change return type when initialized
        return 1;
    }

    /**
     * method to indicate which type of occupants are in the tile
     * This is probably a great use of the generic for next sprint!!!
     *
     * @author Vincent Capra
     * @return boolean true if occupied
     */
    protected void changeTileDisplay(){
        //(set color related to nationality
        //OR place picture of alien race)

        // put the count of occupants on the tile too
    }

}
