package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

/**
 * A class to define an exception thrown during tile placement
 * if there are no available tiles adjacent to the origin pointer.
 *
 * @author Vincent Capra
 * @version 1.0
 * @since 2023-10-29
 */
public class NoAvailableTilesException extends Exception{
    NoAvailableTilesException(String message){
        super(message);
    }
}
