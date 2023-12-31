package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

/**
 * In the Sprint 1 Abstract Factory pattern this is
 * the Concrete Factory participant
 *
 * @author Vincent Capra
 */
public class LifeFormFactory implements AbsLifeFormFactory {


    @Override
    public LifeForm makeLifeForm(String life_form_class, TerrainTile spawnTile) {

        // VC - next 2 lines takes something like [...].alieninvasionsim.Lifeforms.Human
        // and returns "human"
        String manipulated_string = life_form_class.toLowerCase();
        manipulated_string =
                manipulated_string.substring(manipulated_string.lastIndexOf("."));

        if(manipulated_string.contains("human"))
            return new Human(spawnTile);
        else if (manipulated_string.contains("xenomorph"))
            return new Xenomorph(spawnTile);
        else if (manipulated_string.contains("martian"))
            return new Martian(spawnTile);
        else if (manipulated_string.contains("vulcan"))
            return new Vulcan(spawnTile);
        else return new Saiyan(spawnTile);
    }
}
