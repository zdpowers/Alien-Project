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
    //VC - 11/25/23 getting rid of this and placing a single population variable instead
    //private List<LifeForm> mOccupants = new ArrayList<>();
    private LifeForm mOccupant;
    private LifeFormFactory mTileLifeFormFactory = new LifeFormFactory();

    public TerrainTile(int column, int row) {
        super(column, row);
        // Need to place the tile on the board
    }

    /**
     * Method to indicate if the tile is occupied
     *
     * @return boolean true if occupied
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-10-29
     */
    public boolean tileIsOccupied() {
        return (mOccupant != null);
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
        String class_of_current_occupants = occupant.getClass().toString();
        //VC - creates a new lifeform of the same type and adds it to this tile
        //occupant.add(mTileLifeFormFactory.makeLifeForm(class_of_current_occupants, this));

    }

    @Override
    public TerrainTile accept(TileVisitor visitor) {
        return visitor.visitTerrainTile(this);
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

        if(this.mColumnPosition == checkEqualTerrainTile.mColumnPosition
                && this.mRowPosition == checkEqualTerrainTile.mRowPosition)
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
        hash += Math.pow(mColumnPosition, 2);
        hash += Math.pow(mRowPosition, 2);
        hash += Math.pow((mColumnPosition + mRowPosition), 3);
        return hash;
    }
}
