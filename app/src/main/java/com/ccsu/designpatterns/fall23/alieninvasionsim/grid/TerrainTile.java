package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;
import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeFormFactory;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to build single terrain tiles. These are tiles that can be
 * occupied by humans or a single race/species of aliens. The Class of
 * occupant must be homogenous at all times.
 *
 * @author Vincent Capra, Zack Powers
 * @version 1.0
 * @since 2023-10-29
 */
public class TerrainTile extends Tile {

    public TerrainTile(int column, int row) {
        super(column, row);
        // Need to place the tile on the board
    }

    /**
     * method to indicate which type of occupants are in the tile
     * This is probably a great use of the generic for next sprint!!!
     * See below generateLifeform() to get a format to implement if
     * we end up needing this method
     *
     * @author N/A
     * @version N/A
     * @since N/A
     */
    public void typeOfOccupants() {}


    @Override
    public void accept(TileVisitor visitor) {
        visitor.visitTerrainTile(this);
    }

    /**
     * Construct a proper equals method for the class
     *
     * @author Vincent Capra
     * @param obj is a generic Object variable which will be cast
     *           to a Resource Tile
     * @return boolean indicating if the passed object is equal to the calling
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) { return false; }
        TerrainTile checkEqualTerrainTile = (TerrainTile)obj;

        if(this.getmColumnPosition() == checkEqualTerrainTile.getmColumnPosition()
                && this.getmRowPosition() == checkEqualTerrainTile.getmRowPosition())
            return true;
        else
            return false;
    }

    /**
     * Construct a proper hashcode method for the class
     *
     * @author Vincent Capra
     * @return int of the hashcode value for this object
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += Math.pow(getmColumnPosition(), 2);
        hash += Math.pow(getmRowPosition(), 2);
        hash += Math.pow((getmColumnPosition() + getmRowPosition()), 3);
        return hash;
    }
}
