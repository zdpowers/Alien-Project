package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

public class FloodingWeatherStrategy implements WeatherStrategy {
    @Override
    public void applyWeatherEffect(Tile cell) {
        cell.applyBuffDebuff(BuffDebuffTypes.ATTACK_DEBUFF, -2);
        cell.applyBuffDebuff(BuffDebuffTypes.SPEED_DEBUFF, -2);
    }
}
