package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

public class OneUpReproductionStrat implements ReproduceStrategy{
    @Override
    public int reproduceStratMethod(int starting_population) {
        return 1;
    }
}
