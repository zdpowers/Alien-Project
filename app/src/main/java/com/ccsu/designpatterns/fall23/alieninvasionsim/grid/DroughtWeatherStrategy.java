package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

public class DroughtWeatherStrategy implements WeatherStrategy {
    private int duration;

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void applyWeatherEffect(Tile cell, WeatherContext weatherContext){
        cell.applyBuffDebuff(BuffDebuffTypes.ATTACK_BUFF,4);
    }
}
