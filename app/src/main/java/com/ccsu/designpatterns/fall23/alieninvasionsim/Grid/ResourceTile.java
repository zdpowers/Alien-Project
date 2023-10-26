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
    public ResourceTile(int x_coord, int y_coord, String resource_type) {
        super(x_coord, y_coord);
        Random randomizedResourceValue = new Random();
        //VC - assuming that a resource is boarded on all sides for max occupancy
        // that's 200 mining 1 per year for a 25 - 55 year period is
        // between 5,000 and 11,000. May need to tweak later
        unitsOfResource = randomizedResourceValue.nextInt(6000)+5000;

        //VC - need to check if resource_type string is valid,
        // else throw an error before setting
        resourceType = resource_type;

        changeTileDisplay();
    }

    protected void changeTileDisplay(){
        //(set color related to nationality
        //OR place picture of alien race)

        // put the count of occupants on the tile too
    }

}
