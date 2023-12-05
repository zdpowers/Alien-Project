package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

/**
 * The WeatherStrategy interface declares a set of setters and getters methods
 * @author Rocky Trinh
 * @version 1.0
 * @since 2023-05-12
 */
public interface WeatherStrategy {
    void applyWeatherEffect(Tile cell, WeatherContext weatherContext);

    int getDuration();

    void setDuration(int duration);
}
