package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;

/**
 * An interface to detail all progress
 * that a progressible unit might need to handle.
 *
 * @author Joseph Lumpkin
 * @version 1.1
 * @since 2023-26-10
 */
public interface ProgressibleLifeForm {

    /**
     * Protected hook to call the private, local methods
     * responsible for this LifeForm's progression
     * throughout time in the simulation.
     *
     * @param grid  Grid object in which the unit resides in.
     *                  (Not the tile)
     *
     * @author  Joseph Lumpkin
     * @version 1.1
     * @since 2023-26-10
     */
    void progress(Grid grid);
}
