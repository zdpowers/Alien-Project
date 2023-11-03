package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

/**
 * An exception that is thrown if there are no available tiles found.
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
