package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

import android.service.quicksettings.TileService;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms.LifeForm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class to build single terrain tiles. These are tiles that can be
 * occupied by a single country of humans or a single race of aliens
 *
 * @author Vincent Capra
 */
public class TerrainTile extends GridTile {
    private int[] occupants; //VC - need to change to an array of generic occupants

    public TerrainTile(int column_pos, int row_pos) {
        super(column_pos, row_pos);

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


    /**
     * Construct a proper equals method for the class
     *
     * @author Vincent Capra
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){return false;}
        TerrainTile checkEqualTerrainTile = (TerrainTile)obj;

        if(this.columnPosition == checkEqualTerrainTile.columnPosition
                && this.rowPosition == checkEqualTerrainTile.rowPosition)
            return true;
        else return false;
    }

    /**
     * Construct a proper hashcode method for the class
     *
     * @author Vincent Capra
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += Math.pow(columnPosition, 2);
        hash += Math.pow(rowPosition, 2);
        hash += Math.pow((columnPosition+rowPosition), 3);
        return hash;
    }
}
