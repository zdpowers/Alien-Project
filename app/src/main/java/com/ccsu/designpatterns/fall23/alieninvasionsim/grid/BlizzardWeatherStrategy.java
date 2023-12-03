package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

public class BlizzardWeatherStrategy implements WeatherStrategy {
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
    public void applyWeatherEffect(Tile cell, WeatherContext weatherContext) {
        cell.applyBuffDebuff(BuffDebuffTypes.HP_DEBUFF,-2);
        cell.applyBuffDebuff(BuffDebuffTypes.SPEED_DEBUFF, -2);
        cell.setWeatherContext(weatherContext);
    }
}
