package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms.Human;
import com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms.LifeForm;
import com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms.LifeformFactory;

import java.lang.Math;
import java.util.ArrayList;

/**
 * A class to build single terrain tiles. These are tiles that can be
 * occupied by humans or a single race/species of aliens. The Class of
 * occupant must be homogenous at all times.
 *
 * @author Vincent Capra
 */
public class TerrainTile extends GridTile{
    //VC - not sure if this being an arraylist is the best choice
    private ArrayList<LifeForm> occupants;
    private LifeformFactory tileLifeformFactory = new LifeformFactory();

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
     * See below generateLifeform() to get a format to implement if
     * we end up needing this method
     *
     * @author Vincent Capra
     */
    public void typeOfOccupants(){

    }

    /**
     * method to display to users which type of occupants are in the tile
     * This is probably a great use of the generic for next sprint!!!
     * We can overlay images a human or various types of alien races
     *
     * @author Vincent Capra
     */
    protected void changeTileDisplay(){
        //(set color related to nationality
        //OR place picture of alien race)

        // put the count of occupants on the tile too
    }

    /**
     * method to get the class of the occupants of this tile, then
     * generates a new lifeform of the same type and adds it to this tile
     *
     * @author Vincent Capra
     */
    public void generateLifeform(){
        //VC - gets the class of the lifeforms in this tile
        String class_of_current_occupants = occupants.get(0).getClass().toString();
        //VC - creates a new lifeform of the same type and adds it to this tile
        occupants.add(tileLifeformFactory.makeLifeform(class_of_current_occupants, this));
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
     * @return int of the hashcode value for this object
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
