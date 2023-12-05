package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import java.util.List;
/**
 * The WeatherContext class has most of the logic to apply the appropriate weather condition to the grid
 * @author Rocky Trinh
 * @version 1.0
 * @since 2023-05-12
 */
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
