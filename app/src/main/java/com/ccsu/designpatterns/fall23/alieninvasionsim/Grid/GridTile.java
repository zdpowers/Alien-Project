package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

/**
 * A class to build single tiles within the simulation grid
 *
 * @author Vincent Capra
 */

public abstract class GridTile {

    int columnPosition, rowPosition;
    //VC - if do not implement the setNeighbor methods,
    // these following 4 vars can be deleted
    GridTile up_gridTileNeighbor, down_gridTileNeighbor,
            left_gridTileNeighbor, right_gridTileNeighbor;
    public GridTile(int column_pos, int row_pos) {
        //need to place the tile in the layout visually
        columnPosition = column_pos;
        rowPosition = row_pos;
    }
    public int getColumnPostion(){
        return this.columnPosition;
    }
    public int getRowPostion(){
        return this.rowPosition;
    }
    /**
     * These next 4 classes are used to set the neighboring tile references
     *
     * @author Vincent Capra
     */
    /*
    VC - Trying to implement with the constructor getting an
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
    /**
     * method changes the display of its tile depending on the class of
     * tile that it is.
     *
     * @author
     */
    abstract void changeTileDisplay();


}

