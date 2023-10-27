package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

public class NoAvailableTilesException extends Exception{
    NoAvailableTilesException(String message){
        super(message);
    }
}
