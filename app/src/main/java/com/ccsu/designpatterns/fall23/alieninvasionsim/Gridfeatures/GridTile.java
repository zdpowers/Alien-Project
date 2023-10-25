package com.ccsu.designpatterns.fall23.alieninvasionsim.Gridfeatures;

import android.content.Context;
import android.widget.ImageView;
import com.ccsu.designpatterns.fall23.alieninvasionsim.MainActivity;
import com.ccsu.designpatterns.fall23.alieninvasionsim.R;
/**
 * A class to build single tiles within the simulation grid
 *
 * @author Vincent Capra
 */

public class GridTile {

    private int xPosition, yPosition;
    //VPC - if do not implement the setNeighbor methods,
    // these following 4 vars can be deleted
    GridTile up_gridTileNeighbor, down_gridTileNeighbor,
            left_gridTileNeighbor, right_gridTileNeighbor;
    public GridTile(int x_coord, int y_coord) {
        //need to place the tile in the layout visually
        xPosition = x_coord;
        yPosition = y_coord;
    }

    /**
     * These next 4 classes are used to set the neighboring tile references
     *
     * @author Vincent Capra
     */
    /*
    VPC - Trying to implement with the constructor getting an
    x and y coordinate instead of this


    public void setUpNeighbor(GridTile up_refGridTile){
        up_gridTileNeighbor = up_refGridTile;
    }
    public void setDownNeighbor(GridTile up_refGridTile){
        up_gridTileNeighbor = up_refGridTile;
        //need to call up and SET THEIR right as this ref
    }
    public void setLeftNeighbor(GridTile up_refGridTile){
        up_gridTileNeighbor = up_refGridTile;
    }
    public void setRightNeighbor(GridTile up_refGridTile) {
        up_gridTileNeighbor = up_refGridTile;
        //need to call left and SET THEIR right as this ref
    }
    */
}

