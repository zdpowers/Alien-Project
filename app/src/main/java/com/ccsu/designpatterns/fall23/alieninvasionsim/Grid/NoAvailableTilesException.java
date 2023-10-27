package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

/**
 * A class to define an exception thrown during water tile placement
 * if there are no additional non-water tiles adjacent to the
 * coordinate pointer
 *
 * @author Vincent Capra
 */
public class NoAvailableTilesException extends Exception{
    NoAvailableTilesException(String message){
        super(message);
    }
}
