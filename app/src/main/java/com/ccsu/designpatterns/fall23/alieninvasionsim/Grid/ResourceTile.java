package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;
import java.util.Random;
/**
 * A class to build a resource tile. This tile cannot be occupied.
 *  This type of tile has a finite or infinite (water)amount of
 *  a resource
 *
 * @author Vincent Capra
 */
public class ResourceTile extends GridTile{
    private int unitsOfResource;
    private String resourceType;
    public ResourceTile(int column_pos, int row_pos, String resource_type) {
        super(column_pos, row_pos);
        Random randomizedResourceValue = new Random();
        //VC - assuming that a resource is boarded on all sides for max occupancy
        // that's 200 mining 1 per year for a 25 - 55 year period is
        // between 5,000 and 11,000. May need to tweak later
        unitsOfResource = randomizedResourceValue.nextInt(6000)+5000;

        //VC - need to check if resource_type string is valid,
        // else throw an error before setting
        resourceType = resource_type;

        //VC - NEED TO ADD TO THE HASHSET

        changeTileDisplay();
    }

    public String getResourceType(){
        return resourceType;
    }

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
        ResourceTile checkEqualResourceTile = (ResourceTile)obj;

        if(this.columnPosition == checkEqualResourceTile.columnPosition
                && this.rowPosition == checkEqualResourceTile.rowPosition
                && this.resourceType.equals(checkEqualResourceTile.getResourceType()))
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
        hash += resourceType.hashCode();
        return hash;
    }
}
