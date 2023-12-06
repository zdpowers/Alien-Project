package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

/**
 * Interface for methods to accept a Tile Visitor
 * @author Zack Powers
 * @since 2023-03-12
 */
public interface TileVisitable {

    /**
     * Method to accept visitor objects.
     * @param visitor the Concrete implementation of TileVisitor that is visiting the object.
     * @author Zack Powers
     */
    void accept(TileVisitor visitor);
}
