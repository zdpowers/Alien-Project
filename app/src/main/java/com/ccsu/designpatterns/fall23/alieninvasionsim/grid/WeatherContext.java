package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import java.util.List;

public class WeatherContext {
    private WeatherStrategy weatherStrategy;

    public void setWeatherStrategy(WeatherStrategy weatherStrategy){
        this.weatherStrategy = weatherStrategy;
    }

    // Assuming cell is a single tile
    public void applyWeather(Tile cell) {
        if (weatherStrategy != null) {
            WeatherStrategy.applyWeatherEffect(cell);
        }
    }

    // Method to apply weather to the entire grid
    public void applyWeatherToGrid(List<Tile> tiles) {
        if (weatherStrategy != null) {
            // Iterate through all tiles in the grid and apply the weather effect
            for (Tile cell : tiles) {
                WeatherStrategy.applyWeatherEffect(cell);
            }
        }
    }
}
