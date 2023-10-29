package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms.LifeForm;
import java.util.ArrayList;
/**
 * A class to hold lifeforms and tile data
 *
 *
 * @author Rocky Trinh
 * @version 1.0
 * @since 2023-29-10
 */
public class Grid {
    /** Lifeforms are a list of all lifeforms in the simulation */
    ArrayList<LifeForm> lifeforms = new ArrayList<>();
    //ArrayList<Tiles> tiles = new ArrayList<>();

    /**
     * iterates through the arraylist of all
     * lifeforms in the simulation and calls .progress().
     *  @author Rocky Trinh
     *  @version 1.0
     *  @since 2023-29-10
     */
    public void ProgressLifeforms() {
        for(LifeForm i : lifeforms){
            //lifeform operations
            i.progress(this);
        }
    }
}
