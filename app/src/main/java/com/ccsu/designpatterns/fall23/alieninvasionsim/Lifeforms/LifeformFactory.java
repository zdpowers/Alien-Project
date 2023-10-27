package com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.TerrainTile;

/**
 * In the Sprint 1 Abstract Factory pattern this is
 * the Concrete Factory participant
 *
 * @author Vincent Capra
 */
public class LifeformFactory implements AbsLifeformFactory{

    @Override
    public LifeForm makeLifeform(String life_form_class, TerrainTile spawn_tile) {


        //VC - next 2 lines takes something like [...].alieninvasionsim.Lifeforms.Human
        // and returns "human"
        String manipulated_string = life_form_class.toLowerCase();
        manipulated_string =
                manipulated_string.substring(manipulated_string.lastIndexOf("."));

        if(manipulated_string.equals("human"))
            return new Human(spawn_tile);
        else if (manipulated_string.equals("xenomorph"))
            return new Xenomorph();
        else if (manipulated_string.equals("martian"))
            return new Martian();
        else if (manipulated_string.equals("vulcan"))
            return new Vulcan();
        else return new Saiyan();
    }
}
