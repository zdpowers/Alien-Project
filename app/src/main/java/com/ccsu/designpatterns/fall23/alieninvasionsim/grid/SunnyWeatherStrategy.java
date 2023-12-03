package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

public class SunnyWeatherStrategy implements WeatherStrategy{
    @Override
    public void applyWeatherEffect(Tile cell) {
        // Implement sunny weather effects on the grid cell
        cell.applyBuffDebuff(BuffDebuffTypes.ATTACK_BUFF, 2);
        cell.applyBuffDebuff(BuffDebuffTypes.HP_BUFF, 2);
    }
}
