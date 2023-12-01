package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import java.util.Random;
import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;

public class HumanBaseReproductionStrat implements ReproduceStrategy{

    public int reproduceStratMethod(int starting_population) {
        Random randomNum = new Random();

        // VC - if the current tile population is over 5 and spawn a new person, spawn them
        //  into an adjacent tile.
       // if()
        // VC - if there are 2 humans and some RNG to increase the population
        if (starting_population > 2
                && randomNum.nextInt(5) == 3){
            return starting_population + 1;
        }
        else return starting_population;
    }
}
