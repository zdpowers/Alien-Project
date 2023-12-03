package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

public interface WeatherStrategy {
    void applyWeatherEffect(Tile cell, WeatherContext weatherContext);

    int getDuration();

    void setDuration(int duration);
}
