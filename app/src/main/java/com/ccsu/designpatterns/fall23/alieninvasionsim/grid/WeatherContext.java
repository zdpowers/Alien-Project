package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import java.util.List;

public class WeatherContext {
    private WeatherStrategy weatherStrategy;

    public void setWeatherStrategy(WeatherStrategy weatherStrategy) {
        this.weatherStrategy = weatherStrategy;
    }

    // Assuming cell is a single tile
    public void applyWeather(Tile cell) {
        if (weatherStrategy != null) {
            weatherStrategy.applyWeatherEffect(cell, this);
        }
    }

    // Method to apply weather to the entire grid
    public void applyWeatherToGrid(List<Tile> tiles, WeatherContext weatherContext) {
        if (weatherStrategy != null) {
            // Iterate through all tiles in the grid and apply the weather effect
            for (Tile cell : tiles) {
                weatherStrategy.applyWeatherEffect(cell, weatherContext);
            }
        }
    }

    //returns the current weather strategy
     public WeatherStrategy getWeather() {
        return weatherStrategy;
     }

}
