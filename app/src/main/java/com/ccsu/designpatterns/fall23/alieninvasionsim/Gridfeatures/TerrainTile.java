package com.ccsu.designpatterns.fall23.alieninvasionsim.Gridfeatures;

import android.service.quicksettings.TileService;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms.Lifeform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class to build single terrain tiles. These are tiles that can be
 * occupied by a single country of humans or a single race of aliens
 *
 * @author Vincent Capra
 */
public class TerrainTile extends GridTile{
    private int[] occupants; //VC - need to change to an array of generic occupants

    public TerrainTile(int x_coord, int y_coord) {
        super(x_coord, y_coord);

        //need to place the tile on the board
    }

    //not sure if this is implemented already
/*    public class GameBoard {
        private List<Lifeform> lifeforms; //store lifeforms on gameboard
        private List<Tile> tiles; // store tiles on gameboard

        //constructor
        public GameBoard(List<Lifeform> lifeforms, List<Tile> tiles) {
            this.lifeforms = lifeforms;
            this.tiles = tiles;
        }*/

        //create and return a lifeform iterator
        public Iterator<Lifeform> createLifeformIterator() {
            return new LifeformIterator();
        }

        /**
         * The createLifeformIterator is a method that returns an iterator for the Lifeform iterator
         * The LifeformIterator is a private inner class that implements the iterator.
         *
         * @author Rocky Trinh
         */

        private class LifeformIterator implements Iterator<Lifeform> {
            private int index = 0; //keep track of current position

            //method to check if there are lifeforms to iterate through
            @Override
            public boolean hasNext() {
                return index < lifeform.size();
            }
            //method to get the next lifeform and update index
            @Override
            public Lifeform next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return lifeform.get(index++);
            }
        }
    }

    public class Main {
        public void main(String[] args) {
            //Create lifeforms and tiles

            List<Lifeform> lifeforms = new ArrayList<>();
            List<Tiles> tiles = new ArrayList<>();

            //populate lifeforms and tiles
            GameBoard gameBoard = new GameBoard(lifeforms, tiles);

            //iterate through the lifeforms
            Iterator<Lifeform> LifeformIterator = gameBoard.createLifeformIterator();
            while (lifeformIterator().hasNext()) {
                Lifeform lifeform = lifeformIterator.next();
                //lifeform actions can go here
            }
        }
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
