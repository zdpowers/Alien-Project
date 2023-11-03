package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;
import java.util.Random;

/**
 * A class to build a resource tile. This tile cannot be occupied.
 * This type of tile has a finite or infinite (water)amount of a resource.
 *
 * @author Vincent Capra
 * @version 1.0
 * @since 2023-10-26
 */
public class ResourceTile extends Tile {

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
    }

    /**
     * Return the resource type.
     *
     * @return resourceType Type of resource as a String.
     *
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-10-29
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Construct a proper equals method for the class
     *
     * @author Vincent Capra
     * @param obj is a generic Object variable which will be cast
     *            to a Resource Tile
     * @return boolean indicating if the passed object is equal to the calling
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){return false;}
        ResourceTile checkEqualResourceTile = (ResourceTile)obj;

        if(this.mColumnPosition == checkEqualResourceTile.mColumnPosition
                && this.mRowPosition == checkEqualResourceTile.mRowPosition
                && this.resourceType.equals(checkEqualResourceTile.getResourceType()))
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
        hash += Math.pow(mColumnPosition, 2);
        hash += Math.pow(mRowPosition, 2);
        hash += Math.pow((mColumnPosition + mRowPosition), 3);
        hash += resourceType.hashCode();
        return hash;
    }
}
