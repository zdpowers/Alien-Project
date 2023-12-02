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
    private LifeFormFactory mTileLifeFormFactory = new LifeFormFactory();

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

    /**
     * method to display to users which type of occupants are in the tile
     * This is probably a great use of the generic for next sprint!!!
     * We can overlay images a human or various types of alien races
     *
     * @author N/A
     * @version N/A
     * @since N/A
     */
    protected void changeTileDisplay() {
        //(set color related to nationality
        //OR place picture of alien race)
        // put the count of occupants on the tile too
    }

    /**
     * method to get the class of the occupants of this tile, then
     * generates a new lifeform of the same type and adds it to this tile
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-10-29
     */
    public void generateLifeform() {
        //VC - gets the class of the lifeforms in this tile
        String class_of_current_occupants = getOccupant().getClass().toString();
        //VC - creates a new lifeform of the same type and adds it to this tile
        //occupant.add(mTileLifeFormFactory.makeLifeForm(class_of_current_occupants, this));

    }

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
